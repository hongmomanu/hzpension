/**
 * Created by jack on 14-1-6.
 */

define(function(){

    var a={

        ajaxLoading:function(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        },
        ajaxLoadEnd:function (){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }
    }

    return a;
});
