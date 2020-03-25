(function() {
	"use strict";

	// custom scroll bar
	$("html").height(window.innerHeight).niceScroll({
		styler : "fb",
		cursorcolor : "#F2B33F",
		cursorwidth : '6',
		cursorborderradius : '10px',
		background : '#424f63',
		spacebarenabled : false,
		cursorborder : '0',
		zindex : '1000'
	});

	$( window ).resize(function() {
		$("html").height(window.innerHeight).niceScroll({
			styler : "fb",
			cursorcolor : "#F2B33F",
			cursorwidth : '6',
			cursorborderradius : '10px',
			background : '#424f63',
			spacebarenabled : false,
			cursorborder : '0',
			zindex : '1000'
		});
	});
	
	$(".scrollbar1").height(window.innerHeight).niceScroll({
		styler : "fb",
		cursorcolor : "rgb(93, 138, 211)",
		cursorwidth : '6',
		cursorborderradius : '0',
		autohidemode : 'false',
		background : '#F1F1F1',
		spacebarenabled : false,
		cursorborder : '0'
	});
	
	$( window ).resize(function() {
		$(".scrollbar1").height(window.innerHeight).niceScroll({
			styler : "fb",
			cursorcolor : "rgb(93, 138, 211)",
			cursorwidth : '6',
			cursorborderradius : '0',
			autohidemode : 'false',
			background : '#F1F1F1',
			spacebarenabled : false,
			cursorborder : '0'
		});
	});

	$(".scrollbar1").getNiceScroll();
	if ($('body').hasClass('scrollbar1-collapsed')) {
		$(".scrollbar1").getNiceScroll().hide();
	}

})(jQuery);
