
const type = document.querySelector("#type");
const value = document.querySelector("#value");
const free = document.querySelectorAll('[name="free"]');
const included = document.querySelectorAll('[name="included"]');

for (let i = 0; i < free.length; i++) {
    free[i].addEventListener("click", ()=>{
        if( type.options[type.selectedIndex].value === "AxB" && included[i].checked ){
            free[i].toggleAttribute;
        }else{
            free[i].checked = false;
        }
    });
    
}

for (let i = 0; i < included.length; i++) {
    included[i].addEventListener("click", ()=>{
        if( !included[i].checked ){
            free[i].checked = false;
        }
    });
    
}

type.addEventListener("change", ()=>{
    if(type.options[type.selectedIndex].value === "AxB"){
        value.value = 0;
        value.setAttribute("readonly", "readonly");
    }else{
        value.removeAttribute("readonly");
    }

    for (let i = 0; i < free.length; i++) {
        free[i].checked = false;
    }
});

