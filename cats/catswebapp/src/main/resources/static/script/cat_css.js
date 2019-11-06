"use strict";
var cat = {};

cat.replaceByForm = function () {
            cat.hide(document.getElementById("info"));
            cat.show(document.getElementById("form"));
            console.log("replaceByForm end!");
        }

cat.replaceByInfo = function () {
            cat.hide(document.getElementById("form"));
            cat.showFlex(document.getElementById("info"));
            console.log("replaceByInfo end!");
        }

cat.hide = function (element){
            element.style.setProperty("display", "none", "important");
        }

cat.show = function (element){
            //element.style.cssText = "display:block !important";
            //element.setAttribute('style', 'display:block !important');
            element.style.setProperty("display", "block", "important");
        }

cat.showFlex = function (element){
            element.style.setProperty("display", "flex", "important");
        }