/**
 * Created by jack on 13-12-31.
 */
define(['views/MainTree','commonfuncs/LoadingMask'], function(MainTree,LoadingMask){

    function start(){
        addAccordion();
        //MainTree.render({LoadingMask:LoadingMask});
        /*require(['commonfuncs/initFuncs'],function(initFuncs){
            initFuncs.initProcessFromRole(roleid,"流程操作");
        })*/
    }
    function addAccordion(){
        var menu_pension=$('#menu_pension');
        menu_pension.accordion({
            onSelect:function(title,index){
                var panel=$(this).accordion('getPanel',index);
                if(panel.attr('wasSelected')){
                    return;
                }
                panel.attr('wasSelected',true);
                $(panel).append('<ul class="easyui-tree"></ul>');
                require(['views/AccordionTree'],function(js){
                    js.render(panel)
                })
            }

        });

        var getAccordion=function(res){
            var isselected=true;
            $(eval(res)).each(function(i){
                var me=$(this);
                if(me.attr('leaf')=='true')return;//如果是叶子,则没有这个抽屉

                menu_pension.accordion('add', {
                    title: me.attr('text'),
                    selected: isselected
                }).children(':last').attr('functionid',me.attr('id'));
                if(isselected){
                    isselected=false;//第一个叶子默认打开
                }
            })

        }
        //加载抽屉
        $.ajax(
            {
                url:'/businessmenu',
                success:getAccordion
            }
        )

    }


    return {
        start:start
    };
});
