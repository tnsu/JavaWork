$(document).ready(function () {
    var $div2 = $('#eventpage2 .warpP');
    var $div3 = $('#eventpage3 .warpP');
    var $div4 = $('#eventpage4 .text');

    $div4.css({'bottom' : '-800px'});

    $(window).scroll( function(){

    if($("a#p2").hasClass("active")){
        $div2.animate({'opacity' : "1"},600);
    }
   if($("a#p3").hasClass("active")){
        $div3.animate({'opacity' : "1"},600);
    }
    if($("a#p4").hasClass("active")){
        $div4.animate({'bottom' : '0px'},1000);
    }
});


});
