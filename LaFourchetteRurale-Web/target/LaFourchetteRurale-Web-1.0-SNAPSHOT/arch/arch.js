

function addHeader(){
    fetch("./arch/header.html")
        .then(response => response.text())
        .then(html => document.getElementById("header").innerHTML = html)
}  

function addFooter(){
    fetch("./arch/footer.html")
        .then(response => response.text())
        .then(html => document.getElementById("footer").innerHTML = html)
}  


function addNavLi(){
    json = JSON.parse(localStorage.getItem("role"))
    if(json == null){
        console.log("tessssssst");
        fetch("./arch/nav-base.html")
        .then(response => response.text())
        .then(html => document.querySelector(".navbar-nav").innerHTML = html)

    } else {
        switch(json){
            case "admin" : 
                fetch("./arch/nav-admin.html")
                .then(response => response.text())
                .then(html => document.querySelector(".navbar-nav").innerHTML = html)
                break;
            case "deliveryman" :
                fetch("./arch/nav-deliveryman.html")
                .then(response => response.text())
                .then(html => document.querySelector(".navbar-nav").innerHTML = html)
                break;
            case "member" :
                fetch("./arch/nav-member.html")
                .then(response => response.text())
                .then(html => document.querySelector(".navbar-nav").innerHTML = html)
                break;
        }
    }


}  
addHeader()
addFooter()
addNavLi()