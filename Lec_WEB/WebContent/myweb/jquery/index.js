$(document).ready(function(){
    $('header').hover(function(){
        $(this).css('background-color','white');
        $('nav.nav').css('background-color','white');
      
	},function(){
        $(this).css('background-color','#fff0');
        $('nav.nav').css('background-color','#fff0');
     
	});
});