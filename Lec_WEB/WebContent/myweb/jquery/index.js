$(document).ready(function () {

        // 메뉴 서브
        $(".navul li").mouseenter(function () {
                $(".subMenu").css('display', 'block');
                $(".overlay").css('display', 'block');
        });

        $(".no").mouseleave(function () {
                $(".subMenu").css('display', 'none');
                $(".overlay").css('display', 'none');
        });

        // haeder
        $('header').mouseenter(function () {
                $(this).css('background-color', 'white');
                $('nav.nav').css('background-color', 'white');
        });
        $('header').mouseleave(function () {
                $(this).css('background-color', '#fff0');
                $('nav.nav').css('background-color', '#fff0');
        });
        $(".subMenu").mouseenter(function () {
                $("header").css('background-color', 'white');
                $('nav.nav').css('background-color', 'white');
                $(this).css('display', 'block');
        });
        $(".subMenu").mouseleave(function () {
                $("header").css('background-color', '#fff0');
                $('nav.nav').css('background-color', '#fff0');

        });
        //  메인메뉴 하버시 서브메뉴 보이기
        $(".navul li").mouseenter(function () {
                $(".subMenuBar").css('display', 'none');
                $(".subMenuBar_" + $(this).attr('class')).css('display', 'block');
        });
        
        // 팝업
        $('.close').click(function(){
                $('#popup').hide();
        });

        // 서치박스
        $('a.searchBar').click(function(){
                $('.searchBox').show();
        });
        $('.close2').click(function(){
                $('.searchBox').hide();
        });
/* 
        $('.footconnectW').scroll(function () { 
                
        }); */
      

});
            