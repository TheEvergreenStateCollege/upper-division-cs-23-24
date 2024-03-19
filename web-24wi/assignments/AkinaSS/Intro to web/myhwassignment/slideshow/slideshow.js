(function ($) {
  // With strict mode, you can not, for example, use undeclared variables.
  // 'use strict';

  $.fn.slideshowPlugin = function (options) {

    // PARAMETERS
    var params = $.extend({
      slidesBox: 'slides-box',
      nextBtn: 'next-btn',
      prevBtn: 'prev-btn',
      effect: null,
      slideSpeed: 300,
      titleSpeed: 800,
      autoplay: null,
      delay: 5000,
      fadein: null,
      noDelay: 0,
      ratio: null,
    }, options);

    // VARIABLES
    var $slideshow = $(this),
      // COMPONENTS
      $next = $slideshow.find('.' + params.nextBtn + ''),
      $prev = $slideshow.find('.' + params.prevBtn + ''),
      $itemsBox = $slideshow.find('.' + params.slidesBox + ''),
      $items = $itemsBox.find('>div'),
      $title = $slideshow.find('.title'),
      
      // DIMENSIONS
      $itemsNbr = $items.length,
      ratio = $slideshow.height() / $slideshow.width(),
      $slideshowWidth, 
      $slideshowHeight;

    // STYLES
    $slideshow.css('overflow', 'hidden');
    $itemsBox.find('>div:last-child').prependTo($itemsBox);
    $itemsBox.css({
      'height': '100%',
      'position': 'relative'
    });
    $items.css({
      'height': '100%',
      'float': 'left'
    });

    // SIZING
    function sizingSlides() {
      $slideshowWidth = $slideshow.width();
      $items.width($slideshowWidth);
      $itemsBox.width($itemsNbr * $slideshowWidth);
      $itemsBox.css('left', -$slideshowWidth);
      if (params.ratio === 'keep') {
        $slideshowHeight = Math.round($slideshowWidth * ratio); 
        // keep aspect ratio
        $slideshow.height($slideshowHeight);
      }
    }
    sizingSlides();
    $(window).resize(sizingSlides);

    // EVENTS & EFFECTS
    function next() {

      // DEFAULT SLIDING EFFECT
      if (params.effect === null || params.effect === 'sliding') {

        $title.fadeOut(100);

        $itemsBox.animate({
          'margin-left': '-=' + $slideshowWidth
        }, params.slideSpeed, function () {

          var $this = $(this);
          $this.find('>div:first-child').appendTo($this);
          $title.fadeIn(150);
          $title.css('color', 'black');
          $this.css('margin-left', 0);

        });

      }

      // FADE EFFECT
      if (params.effect === 'fade') {

        $title.fadeOut(100);

        $itemsBox.animate({
          opacity: 0.8
        }, 200);

        $itemsBox.animate({
          'margin-left': '-=' + $slideshowWidth
        }, params.noDelay, function () {

          var $this = $(this);
          $this.find('>div:first-child').appendTo($this);
          $title.fadeIn(400);
          $itemsBox.animate({
            opacity: 1
          }, 600);
          $this.css('margin-left', 0);

        });

      }

      // PHOTO EFFECT
      if (params.effect === 'photo') {

        $itemsBox.animate({
          'margin-left': '-=' + $slideshowWidth
        }, params.noDelay, function () {

          var $this = $(this);
          $this.find('>div:first-child').appendTo($this);
          $this.css('margin-left', 0);

        });

      }

    } // END NEXT SLIDE

    function prev() {

      if (params.effect === null || params.effect === 'sliding') {

        $title.css('color', 'transparent');
        $title.animate({
          'margin-left': '-=' + $slideshowWidth * 2
        }, params.titleSpeed, function () {
          var $this = $(this);
          $this.find('>div:last-child').prependTo($this);
          $title.css('color', 'black');
          $this.css('margin-left', 0);
        });

        $itemsBox.animate({
          'margin-left': '+=' + $slideshowWidth
        }, params.slideSpeed, function () {
          var $this = $(this);
          $this.find('>div:last-child').prependTo($this);
          $this.css('margin-left', 0);
        });
      }

      // FADE EFFECT
      if (params.effect === 'fade') {

        $title.fadeOut(100);
        $itemsBox.animate({
          opacity: 0.8
        }, 200);

        $itemsBox.animate({
          'margin-left': '+=' + $slideshowWidth
        }, params.noDelay, function () {

          var $this = $(this);
          $this.find('>div:last-child').prependTo($this);
          $title.fadeIn(400);
          $itemsBox.animate({
            opacity: 1
          }, 600);
          $this.css('margin-left', 0);

        });

      }

      // PHOTO EFFECT
      if (params.effect === 'photo') {

        $itemsBox.animate({
          'margin-left': '+=' + $slideshowWidth
        }, params.noDelay, function () {

          var $this = $(this);
          $this.find('>div:last-child').prependTo($this);
          $this.css('margin-left', 0);

        });

      }

    } // END PREVIOUS SLIDE

    // AUTOPLAY
    if (params.autoplay === 'true') {
      setInterval(function () {
        next();
      }, params.delay);
    }

    // NAVIGATION
    $next.on('click', next);
    $prev.on('click', prev);

    return $slideshow;

  };

}(jQuery));