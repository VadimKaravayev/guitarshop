$(".addToCart").click(function () {
    var id = $(this).attr('productid');
    var btn = $(this);
    $.ajax({
      url: "cart?id=".concat(id),
      type: "POST",
      success: function(data){
           $("#cartCount").text(data.count + " items in cart");
           btn.attr('disabled', '');
        },
        error:function (xhr, ajaxOptions, thrownError){
            if(xhr.status==404) {
                alert("Invalid request");
            }
        }
    });
});

$(".removeFromCart").click(function () {
    var id = $(this).attr('productid');
    var btn = $(this);
    $.ajax({
      url: "cart?id=".concat(id),
      type: "DELETE",
      success: function(data){
           $("#cartCount").text(data.count);
           btn.attr('disabled', '');
        },
        error:function (xhr, ajaxOptions, thrownError){
            if(xhr.status==404) {
                alert("Invalid request");
            }
        }
    });
});

$('.minus-btn').on('click', function() {
    var btn = $(this);

    var input = $(this).parent().find("input");
    var btnPlus = $(this).parent().find(".plus-btn");
    var count = input.val();
    var intCount = parseInt(count) - 1;

    var id = $(this).parent().attr('productid');
    if(intCount > 0){
        $.ajax({
          url: "cart?id=".concat(id).concat("&count=").concat(intCount),
          type: "PUT",
          success: function(data){
               $("#allTotalPrice").text("Total sum:" + data.totalPrice);
               input.val(intCount);
               if(intCount == 1){
                    btn.attr('disabled', '');
               }
               btnPlus.removeAttr('disabled');
            },
            error:function (xhr, ajaxOptions, thrownError){
                if(xhr.status==400) {
                    alert("Invalid request");
                }
            }
        });
    }
});

$('.plus-btn').on('click', function(e) {
    var btn = $(this);
    var input = $(this).parent().find("input");
    var count = input.val();
    var btnMinus = $(this).parent().find(".minus-btn");
    var intCount = parseInt(count) + 1;

    var id = $(this).parent().attr('productid');

    if(intCount <= 100){
        $.ajax({
          url: "cart?id=".concat(id).concat("&count=").concat(intCount),
          type: "PUT",
          success: function(data){
               $("#allTotalPrice").text("Total sum:" + data.totalPrice);
               input.val(intCount);
               if(intCount === 100){
                    btn.attr('disabled', '');
               }
               btnMinus.removeAttr('disabled');
            },
            error:function (xhr, ajaxOptions, thrownError){
                if(xhr.status==400) {
                    alert("Invalid request");
                }
            }
        });
    }

});

$('.remove-btn').on('click', function(e) {
    var btn = $(this);
    var item = $(this).parent().parent();

    var id = $(this).attr('productid');

    $.ajax({
      url: "cart?id=".concat(id),
      type: "DELETE",
      success: function(data){
           $("#allTotalPrice").text("Total sum:" + data.totalPrice);
            item.remove();
        },
        error:function (xhr, ajaxOptions, thrownError){
            if(xhr.status==400) {
                alert("Invalid request");
            }
        }
    });
});

$('.remove-all-btn').on('click', function(e) {
    $.ajax({
      url: "cart",
      type: "DELETE",
      success: function(data){
           $("#allTotalPrice").text("Total sum:" + 0);
           $("#cart").remove();
        },
        error:function (xhr, ajaxOptions, thrownError){
            if(xhr.status==400) {
                alert("Invalid request");
            }
        }
    });
});