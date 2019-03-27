document.addEventListener("DOMContentLoaded", () => {
//    checkStatus();
    var count = 0;
    var prevExtraPriceNum = 0.00;
    document.querySelectorAll(".customize").forEach(btn => {
        btn.onclick = () => {
            prevExtraPriceNum = 0.00;
        }
    });

   // add current meal to side cart
   document.querySelectorAll(".add-to-cart").forEach(btn => {
       btn.onclick = () => {
            // if an item is added to cart, then show on side bar
           if(document.querySelector(".state-menu").style.display === "none"){
               showSideCart();
           };

            // hide modal
            modalID = "#" + btn.parentElement.parentElement.parentElement.parentElement.id;
            $(modalID).modal("hide");

            // get name and price
            var modalContent = btn.parentElement.parentElement;
            var mealName = modalContent.querySelector(".modal-title").innerHTML;
            var extrasName = [];

            // extra for extra radio
            modalContent.querySelectorAll(".extra-radio").forEach(extraRadio => {
                if(extraRadio.checked){
                    extrasName.push(extraRadio.value);
                }
            });

            // extra for extra checkbox
            modalContent.querySelectorAll(".extra-checkbox").forEach(extraCheckbox => {
                if(extraCheckbox.checked){
                    extrasName.push(extraCheckbox.value);
                }
            });

            var mealPrice = btn.children[1].innerHTML;
            var mealPriceNum = parseFloat(mealPrice.substring(1));

            // add (mealName, price) to cart menu on the head
//            var div = document.querySelector(".cart-items");
//            var a = document.createElement("a");
//            a.setAttribute("href", "#");
//            a.setAttribute("class", "dropdown-item");
//            a.innerHTML = `<span class="cart-item">${mealName}</span>
//                            <span class="total">
//                                <span class="price">${mealPrice}</span>
//                            </span>`;
//            div.append(a);

            // add info to side cart
            // check if this item is already in the cart
            var items = document.querySelector("#items-in-cart");
            var exist = contains(mealName, extrasName, mealPriceNum);
            if(exist === true){
                updateTotalPrice(mealPriceNum, "add");
                return;
            }

            // if it is a new item, then add to side cart
            var temp = document.createElement("div");
            temp.setAttribute("class", "meal-in-cart");
            temp.innerHTML = `<h6 class="meal-name-in-cart">${mealName}</h6>`;
            var extrasInfo = "";
            for(var i in extrasName){
                extrasInfo += `<h6 class="extra-name-in-cart">${extrasName[i]}</h6>`;
            }
            temp.innerHTML += extrasInfo;
            temp.innerHTML += `<a class="clear">
                                    <i class="fas fa-trash"></i>
                                </a>
                                <span>
                                    Qty: <span class="quantity">1</span>
                                </span>
                                <a class="add">
                                    <i class="fas fa-plus"></i>
                                </a>
                                <span class="meal-price-in-cart">${mealPrice}</span>
                               `;
            items.append(temp);
            updateTotalPrice(mealPriceNum, "add");
       }
    });

    // choose extra for current meal and update meal price, extra type: radio
   document.querySelectorAll('.extra-radio').forEach(extraRadio => {
        extraRadio.addEventListener('change', () => {
            var extraPrice = extraRadio.nextElementSibling.nextElementSibling;
            var extraPriceNum = 0.00;
            if(extraPrice.className === "extra-price"){
                extraPriceNum = parseFloat(extraPrice.innerHTML.substring(1));
            }

            var modalBody = extraRadio.parentElement;
            var modalFooter = modalBody.nextElementSibling;
            var mealPrice = modalFooter.querySelector(".meal-price");
            var mealPriceNum = parseFloat(mealPrice.innerHTML.substring(1));
            if(extraRadio.checked === true){
                mealPrice.innerHTML = '$' + (mealPriceNum + extraPriceNum - prevExtraPriceNum).toFixed(2);
                prevExtraPriceNum = extraPriceNum;
            }
        });
   });

    // choose extra for current meal and update meal price, extra type: checkbox
    document.querySelectorAll('.extra-checkbox').forEach(extraCheckbox => {
        extraCheckbox.addEventListener('change', () => {
            var extraPrice = extraCheckbox.nextElementSibling.nextElementSibling;
            if(extraPrice.className === "extra-price"){
                var extraPriceNum = 0.00;
                if(extraPrice.className === "extra-price"){
                    extraPriceNum = parseFloat(extraPrice.innerHTML.substring(1));
                }

                var modalFooter = extraCheckbox.parentElement.nextElementSibling;
                var mealPrice = modalFooter.querySelector(".meal-price");
                var mealPriceNum = parseFloat(mealPrice.innerHTML.substring(1));
                if(extraCheckbox.checked === true){
                    mealPrice.innerHTML = '$' + (mealPriceNum + extraPriceNum).toFixed(2);
                }else{
                    mealPrice.innerHTML = '$' + (mealPriceNum - extraPriceNum).toFixed(2);
                }
           }
        });
    });

    // add, remove, clear a meal
    document.addEventListener('click', (e) => {
        var target = e.target;
        var mealInCart = target.parentElement.parentElement;
        if(mealInCart.className === 'meal-in-cart'){
            var mealPriceInCart = mealInCart.querySelector(".meal-price-in-cart");
            var mealPriceNumInCart = parseFloat(mealPriceInCart.innerHTML.substring(1)).toFixed(2);

            var mealQty = mealInCart.querySelector(".quantity");
            var mealQtyNum = parseInt(mealQty.innerHTML);

            var unitPrice = mealPriceNumInCart/mealQtyNum;
            if(target.className === 'fas fa-plus'){
                mealQty.innerHTML = mealQtyNum + 1;
                mealPriceInCart.innerHTML = '$' + (unitPrice*(mealQtyNum + 1)).toFixed(2);
                if(mealQtyNum + 1 === 2) changeIcon(mealInCart, "remove");
                updateTotalPrice(unitPrice, "add");
            }else if(target.className === 'fas fa-minus' || target.className === 'fas fa-trash'){
                mealQty.innerHTML = mealQtyNum - 1;
                mealPriceInCart.innerHTML = '$' + (unitPrice*(mealQtyNum - 1)).toFixed(2);
                console.log("should show clear icon");
                if(mealQtyNum - 1 === 1) changeIcon(mealInCart, "clear");
                updateTotalPrice(unitPrice, "remove");
            }
            if(mealQtyNum - 1 === 0 && target.className === 'fas fa-trash'){
                mealInCart.remove(); // remove item
            };
        };
    });
});

// store opens at 9am and close at 10pm
function checkStatus(){
    var date = new Date();
    var hours = date.getHours();
    // if closed
    if(hours < 9 || hours > 22){
        document.querySelector('#state').innerHTML = ` 
            <div class="detail" id="state-header">
                Restaurant closed
            </div>
            <div class="detail" id="state-content">
                Closed. Ordering starts again Wednesday at 10:00am.
            </div>
        `
    }
}

function showSideCart(){
    document.querySelector(".state-empty-hungry").style.display = "none";
    document.querySelector(".state-menu").style.display = "block";
}


function updateQuantity(item, action){
    var quantity = item.querySelector('.quantity');
    var quantityNum = parseInt(quantity.innerHTML);
    if(action === "add"){
        quantity.innerHTML = quantityNum + 1;

    }else if(action === "remove"){
        quantity.innerHTML = quantityNum - 1;
        if(quantityNum - 1 === 1) changeIcon(item, "clear");
    }
};

function changeIcon(item, icon){
    if(icon === "remove"){
        item.querySelector(".clear").className = "remove";
        item.querySelector(".remove").children[0].className = "fas fa-minus";
    }else if(icon === "clear"){
         item.querySelector(".remove").className = "clear";
         item.querySelector(".clear").children[0].className = "fas fa-trash";
    }
}

function updateTotalPrice(price, action){
    var prevTotalNum = parseFloat(document.querySelector(".total-price").innerHTML.substring(1));
    var totalPrice = 0;
    if(action === "add"){
        totalPrice = prevTotalNum + price;
    }else if(action === "remove"){
        totalPrice = prevTotalNum - price;
    }

    document.querySelectorAll(".total-price").forEach(total => {
        total.innerHTML = "$" + totalPrice.toFixed(2);
        if(totalPrice === 0){
            document.querySelector(".state-menu").style.display = "none";
            document.querySelector(".state-empty-hungry").style.display = "block";
        }
    });
};

function getExtrasName(meal){
    var extrasName = [];
    meal.querySelectorAll(".extra-name-in-cart").forEach(extraNameInCart => {
        extrasName.push(extraNameInCart.innerHTML);
    });
    return extrasName;
}

// check if 2 extra list are exactly the same
function equals(extrasName1, extrasName2){
    // check length
    if(extrasName1.length !== extrasName2.length) return false;

    for(var i = 0; i < extrasName1.length; i++){
        if(extrasName1[i] !== extrasName2[i]){
            return false;
        }
    }

    return true;
}

// check if item is already in the cart, if it is, then update count
function contains(mealName, extrasName, mealPriceNum){
    var flag = false;
    document.querySelectorAll(".meal-in-cart").forEach(meal => {
        var curMealName = meal.querySelector(".meal-name-in-cart").innerHTML;
        var curExtrasName = getExtrasName(meal);
        if(curMealName === mealName && equals(curExtrasName, extrasName)){
            updateQuantity(meal, "add");
            var curPriceNum = parseFloat(meal.querySelector(".item-price").innerHTML.substring(1));
            meal.querySelector(".item-price").innerHTML = '$' + (curPriceNum + mealPriceNum).toFixed(2);
            flag = true;
        }
    });

    return flag;
}

// remove item from cart
function removeItem(name){
    // remove item from cart
    document.querySelectorAll(".cart-item").forEach(span => {
        if(span.innerHTML === name){
            span.parentElement.remove();
        };
    });

    // change back the background of menu
    document.querySelectorAll(".name").forEach(menu_name => {
        if(menu_name.innerHTML === name){
            menu_name.parentElement.parentElement.style.backgroundColor = "";
        }
    });

    // hide the subtotal if the cart is empty, i.e. price = 0.00
    if(document.querySelector(".total-price").innerHTML === '$0'){
        document.querySelector('.state-menu').style.display = "";
        document.querySelector('.state-empty-hungry').style.display = "block";
    };
}