import {setOrderDetailPaymentDate} from "../controllers/orderDetailController.js"


const paimentDetailOrderDate = document.getElementById("paimentDetailOrderDate")
const paimentDetailOrderPrice = document.getElementById("paimentDetailOrderPrice")


const infos = JSON.parse(localStorage.getItem("orderPaiment"))
const dishType = JSON.parse(localStorage.getItem("dishType"))

function getPrixParNom(platNom) {
    // Rechercher le plat par son nom dans le JSON
    const plat = dishType.find(item => item.dishTypeName === platNom);
    // Si le plat est trouvé, retourner son prix, sinon retourner null
    return plat ? plat.price : null;
}

// Exemple d'utilisation
const starterPrice = getPrixParNom("entrée");
const mainCoursePrice = getPrixParNom("plat_principal");
const DessertPrice = getPrixParNom("dessert");


paimentDetailOrderDate.innerHTML = infos.date;
paimentDetailOrderPrice.innerHTML = (infos.orderDetail.length * starterPrice)+(infos.orderDetail.length * mainCoursePrice)+(infos.orderDetail.length * DessertPrice) + " €";

let orderDetailIds = [];
const sendBtn = document.getElementById("nextPageButton");

sendBtn.addEventListener("click", function(){
    if((document.getElementById("numcarte") != "")
    &&(document.getElementById("porteurdate") != "")
    &&(document.getElementById("cryptogramme") != "")){
        for(let i=0; i<infos.orderDetail.length; i++){
            orderDetailIds.push(infos.orderDetail[i].orderDetailId);
        }
    
        console.log(orderDetailIds);
        localStorage.setItem("orderDetailPayment", JSON.stringify(orderDetailIds));
        setOrderDetailPaymentDate();
        window.location.href = "order-visualisation.html"
    } else {
        document.getElementsById("paiementMessage").innerHTML = "Il manque des informations de paiement."
    }

});
