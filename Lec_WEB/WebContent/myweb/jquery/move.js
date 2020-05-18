$(document).ready(function () {
    //
    $('')
    
        /* 사이드 페이지 이동바 active */
    $('#pagemenu ul li a').click(function () {
            $("*").removeClass("active");
            $(this).addClass('active');
    });
/* event.thml */
    $('#sideBar ul li a').click(function(){
        $("*").removeClass("active");
            $(this).addClass("active");
    })

       // 페이지 클릭시 화면 부드럽게 넘어가기
       
       var link = $("#pagemenu a");
       link.on('click', function (event) {
               var target = $($(this).attr('href'));
               $('html, body').animate({
                       scrollTop: target.offset().top
               }, 800);
               $(this).addClass('active');
               event.preventDefault();
       });

       /* event.thml */
        var link1 = $("#sideBar a");
       link1.on('click', function (event) {
               var target = $($(this).attr('href'));
               $('html, body').animate({
                       scrollTop: target.offset().top
               }, 800);
            /*    $(this).addClass('active'); */
               event.preventDefault();
       });

       $(window).on('scroll', function () {
               findPosition();
       });
       function findPosition() {
               $('.section').each(function () {
                       if (($(this).offset().top - $(window).scrollTop()) < 10) {
                               link.removeClass('active');
                               $('#pagemenu').find('[data-scroll="' + $(this).attr('id') + '"]').addClass('active');
                       }
               });
       }

       /* event.thml */
       
    $(window).on('scroll', function () {
               findPosition2();
       });
       function findPosition2() {
               $('.section').each(function () {
                       if (($(this).offset().top - $(window).scrollTop()) < 10) {
                               link1.removeClass('active');
                               $('#sideBar').find('[data-scroll="' + $(this).attr('id') + '"]').addClass('active');
                       }
               });
       }
       findPosition(); 
       findPosition2();

       // 페이지 이동 (한번에)
       window.onload = function () {
               var elm = ".section";
               $(elm).each(function (index) {
                   // 개별적으로 Wheel 이벤트 적용
                   $(this).on("mousewheel DOMMouseScroll", function (e) {
                       e.preventDefault();
                       var delta = 0;
                       if (!event) event = window.event;
                       if (event.wheelDelta) {
                           delta = event.wheelDelta / 120;
                           if (window.opera) delta = -delta;
                       } 
                       else if (event.detail)
                           delta = -event.detail / 3;
                       var moveTop = $(window).scrollTop();
                       var elmSelecter = $(elm).eq(index);
                       // 마우스휠을 위에서 아래로
                       if (delta < 0) {
                           if ($(elmSelecter).next() != undefined) {
                               try{
                                   moveTop = $(elmSelecter).next().offset().top;
                               }catch(e){}
                           }
                       // 마우스휠을 아래에서 위로
                       } else {
                           if ($(elmSelecter).prev() != undefined) {
                               try{
                                   moveTop = $(elmSelecter).prev().offset().top;
                               }catch(e){}
                           }
                       }
                        
                       // 화면 이동 0.8초(800)
                       $("html,body").stop().animate({
                           scrollTop: moveTop + 'px'
                       }, {
                           duration: 800, complete: function () {
                           }
                       });
                   });
               });
           }
});