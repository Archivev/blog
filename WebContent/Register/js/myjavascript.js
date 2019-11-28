$(function(){
	$(window).on('load resize',function(){
		var h1=$(window).height();
		var whole=$('.whole');
		whole.css('top',(h1-whole.height())/2-10+"px");
	})
});