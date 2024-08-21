import getDays from "../controllers/dayController.js"
import getDiet from "../controllers/dietController.js"
import getAllFoodSpecificity from "../controllers/foodSpecificityController.js"
import getCitiesAndSave2 from "../controllers/getCitiesR.js"

// Appel des fonctions pour récupérer les données
Promise.all([getDays(), getDiet(), getAllFoodSpecificity(), getCitiesAndSave2()])
    .then(() => {
        // Les données ont été correctement récupérées
        // Vous pouvez maintenant continuer avec le reste du code

        let member = JSON.parse(localStorage.getItem("constructMember"))
        let diets = JSON.parse(localStorage.getItem("diets"))
        let foods = JSON.parse(localStorage.getItem("foodSpecificity"))
        let days = JSON.parse(localStorage.getItem("days"))

        let selectDiet = document.getElementById("regimeAlimentaire")
        let selectFood = document.getElementById("specificiteAlimentaire")

        // Remplissage des options du select pour les régimes alimentaires
        for (let i = 0; i < diets.length; i++) {
            selectDiet.innerHTML += `<option value="${diets[i].dietId}">${diets[i].name}</option>`;
        }

        // Remplissage des options du select pour les spécificités alimentaires
        for (let i = 0; i < foods.length; i++) {
            selectFood.innerHTML += `<option value="${foods[i].foodSpecificityId}">${foods[i].name}</option>`;
        }

        // Ajout de l'événement submit au formulaire
        document.getElementById("membershipForm").addEventListener("submit", function (event) {
            event.preventDefault(); // Empêche le formulaire de se soumettre

            // Récupération des valeurs des champs
            let genre = Number(document.getElementById("genre").value);
            let date = document.getElementById("dateNaissance").value;
            let telephone = Number(document.getElementById("telephone").value);

            // Vérification si des champs sont vides
            if (date === '' || telephone === '') {
                // Affichage d'un message d'erreur
                alert('Veuillez remplir tous les champs.');
                return; // Empêche la suite du code d'être exécutée
            }

            // Si tous les champs sont remplis, assignation des valeurs à l'objet member
            member.gender = genre;
            member.diet = Number(selectDiet.value);
            member.selectedFood = Number(selectFood.value);
            member.phone = telephone;
            member.birthdate = date;

            localStorage.setItem("constructMember", JSON.stringify(member));

            window.location.href = "livraison.html";
        });
    })
    .catch(error => {
        console.error("Une erreur s'est produite lors de la récupération des données:", error);
    });




