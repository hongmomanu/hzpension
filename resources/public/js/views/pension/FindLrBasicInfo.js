/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-24
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
define(function(){
    function render(parameters,res){
         /*$('#test').bind('click',function(){
             alert("hello");
         })*/

        $('#search').bind('click',function(){
             var searchname = $('#lrname').val();
            $('#lrxxid').datagrid({
                url:'ajax/searchLrBasicInfo.jsp' ,
                queryParams:{
                    lrname:searchname
                }
            });

        });

        $('#edit').bind('click',function(){
            var getrow = $('#lrxxid').datagrid('getSelected');
            if(getrow!=null){
                alert("edit"+getrow.name);
                require(['commonfuncs/TreeClickEvent'],function(ShowContent){
                    ShowContent.ShowContent('text!views/pension/LrBasicInfo.htm','views/pension/LrBasicInfo','修改老年基本信息','','ajax/searchLrBasicInfo.jsp?lrname='+getrow.name);
                })
            }else{
                alert("没选中任何行！");
            }

        })
    }

    return{
        render:render
    }

})
