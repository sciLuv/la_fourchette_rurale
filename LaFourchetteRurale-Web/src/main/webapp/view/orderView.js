import {getorderDetailFromMember} from "../controllers/orderDetailController.js";
import getMembershipOfAMember from "../controllers/membershipController.js";
import getAllDishType from '../controllers/dishTypeController.js';



let memberShipInfos = JSON.parse(localStorage.getItem("membership"))
let memberInfos = JSON.parse(localStorage.getItem("member"))
let orderDetailInfos = JSON.parse(localStorage.getItem("orderDetail"))
let dishType = JSON.parse(localStorage.getItem("dishType"))

Promise.all([getorderDetailFromMember(),getMembershipOfAMember(),getAllDishType()])
  .then(() => {
    memberShipInfos = JSON.parse(localStorage.getItem("membership"))
    memberInfos = JSON.parse(localStorage.getItem("member"))
    orderDetailInfos = JSON.parse(localStorage.getItem("orderDetail"))
    dishType = JSON.parse(localStorage.getItem("dishType"))

    setTimeout(function() {
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
        
        console.log(memberShipInfos);
        const beginningDate = memberShipInfos[0].startDate;
        console.log(beginningDate);
        
        function genererListeDates(date) {
            const [annee, mois, jour] = date.split('-').map(Number);
            const listeDates = [];
            let moisCourant = mois;
            let anneeCourante = annee;
            const dateActuelle = new Date(); // Date actuelle
            
            // Si le premier jour de la date entrée est supérieur à 10, ne pas l'ajouter à la liste
            if (jour > 10) {
                moisCourant++;
                if (moisCourant === 13) {
                    moisCourant = 1;
                    anneeCourante++;
                }
            }
            
            for (let i = 1; i <= 12; i++) {
                // Construire la chaîne de caractères de la date
                let moisString = moisCourant.toString().padStart(2, '0');
                let dateString = `${anneeCourante}-${moisString}-10`;
                
                // Convertir la date courante en objet Date pour la comparaison
                const dateCourante = new Date(dateString);
                
                // Vérifier si la date courante est supérieure ou égale à la date actuelle
                if (dateCourante > dateActuelle) {
                    break; // Sortir de la boucle si la date courante est supérieure à la date actuelle
                }
                
                listeDates.push(dateString);
        
                // Incrémenter le mois
                moisCourant++;
        
                // Si le mois est 13, passer à la prochaine année et recommencer à 1 pour les mois
                if (moisCourant === 13) {
                    moisCourant = 1;
                    anneeCourante++;
                }
            }
            
            // Ajouter le dernier mois même si le 10 n'est pas encore passé
            let dernierMois = moisCourant.toString().padStart(2, '0');
            let dateDernierMois = `${anneeCourante}-${dernierMois}-10`;
            listeDates.push(dateDernierMois);
            
            // Ajouter le mois suivant
            moisCourant++;
            if (moisCourant === 13) {
                moisCourant = 1;
                anneeCourante++;
            }
            let moisSuivant = moisCourant.toString().padStart(2, '0');
            let dateMoisSuivant = `${anneeCourante}-${moisSuivant}-10`;
            listeDates.push(dateMoisSuivant);
            
            return listeDates;
        }
        
        
        let billDateList = genererListeDates(memberShipInfos[0].startDate);
        console.log(billDateList);
        
        let array = []
        for(let i = 0; i < billDateList.length; i++){
            array.push([])
            for(let j = 0; j < orderDetailInfos.length; j++){
                console.log(orderDetailInfos[j]);
                if (orderDetailInfos[j].billDate == billDateList[i]) {
                    array[i].push(orderDetailInfos[j]);
                }
            }
        }
        console.log(array);
        
        
        function supprimerTableauxVides(orderArray, billDateList) {
        
            // Créer un nouveau tableau pour stocker les tableaux non vides et leurs index correspondants
            const newOrderArray = [];
            const newBillDateList = [];
        
            // Parcourir les tableaux
            for (let i = 0; i < orderArray.length; i++) {
                // Vérifier si le tableau actuel est vide
                if (orderArray[i].length === 0) {
                    // Si le tableau est vide, ne rien faire
                    continue;
                }
                
                // Si le tableau n'est pas vide, le conserver et conserver son index correspondant
                newOrderArray.push(orderArray[i]);
                newBillDateList.push(billDateList[i]);
            }
        
            // Retourner les nouveaux tableaux
            return [newOrderArray, newBillDateList];
        }
        
        const [newOrderArray, newBillDateList] = supprimerTableauxVides(array, billDateList);
        let container = document.getElementById("order-container-line");
        
        for(let i = newBillDateList.length - 1; i >= 0; i--){
            if(newOrderArray[i].length > 0){
        
                console.log(memberInfos.id);
                let isPaid         
                if(i == newBillDateList.length - 1){
                    isPaid ="<em style='padding-left : 0px;display: inline-block;transform: translateX(-5px);height: 25px;'>en cours</em>"
                } else if(newOrderArray[i][0].paymentDate == undefined){
                    isPaid = "<a class='btn btn-danger bg-danger pe-1 ps-1' href='paiement.html' style='transform : translateX(-10%);'>À payer</a>"
                }else {
                    isPaid = "Payée"
                }
    
                container.innerHTML += 
                `
                <div class="table-line-container ">
                <span class="table-text col1">${memberInfos.id + "-"+ (i+1) + "-com"}</span>
                <span class="table-text col2">${newBillDateList[i].replace(/-/g, '/')}</span>
                <span class="table-text col3">${(newOrderArray[i].length * starterPrice)+(newOrderArray[i].length * mainCoursePrice)+(newOrderArray[i].length * DessertPrice)}€</span>
                <span class="table-text col3 paimentCommandButton" id="${memberInfos.id + "-"+ (i+1) + "-com"}">${isPaid}</span>
                <div class="flex">
                    <button class="little-btn btn btn-success bg-success no-opacity"> <span>Voir</span> </button>
                </div>
                </div>
                `
    
                if(i==1){
    
                }
            }
            }
            newOrderArray.reverse()
            newBillDateList.reverse()
            let allPaiments = document.querySelectorAll(".paimentCommandButton")
            allPaiments.forEach((element) => {
                element.addEventListener("click", function() {
                    // Obtenir la position de l'élément dans la liste
                    let position = Array.from(allPaiments).indexOf(element);
                    console.log("La position de l'élément dans la liste est :", position);
                    console.log(newOrderArray[position]);
                    console.log(newBillDateList[position]);
                    localStorage.setItem("orderPaiment",JSON.stringify({
                        orderDetail : newOrderArray[position],
                        date : newBillDateList[position]
                    }))
                });
            });
        }, 500)

  })
  .catch(error => {
    console.error("Une erreur s'est produite :", error);
  });

