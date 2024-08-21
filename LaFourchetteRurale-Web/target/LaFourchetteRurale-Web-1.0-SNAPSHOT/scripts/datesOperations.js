export function getNowDate(){
  const currentDate = new Date();

  const year = currentDate.getFullYear();
  const month = String(currentDate.getMonth() + 1).padStart(2, '0'); // Ajoute un zéro devant si le mois est inférieur à 10
  const day = String(currentDate.getDate()).padStart(2, '0'); // Ajoute un zéro devant si le jour est inférieur à 10
  
  const formattedDate = `${year}-${month}-${day}`;
  return formattedDate
}

export function ajouterJours(dateInitiale, joursAajouter) {
  // Création de la date initiale
  const date = new Date(dateInitiale);
  
  // Ajout des jours
  date.setDate(date.getDate() + joursAajouter);

  // Formatage de la date
  const jour = String(date.getDate()).padStart(2, '0');
  const mois = String(date.getMonth() + 1).padStart(2, '0'); // Les mois sont indexés à partir de 0, donc nous ajoutons 1
  const annee = date.getFullYear();

  // Création de la date au format souhaité
  const dateFormatee = `${annee}-${mois}-${jour}`;

  // Retourner la date formatée
  return dateFormatee;
}


export function getNextMonday(date = new Date()) {
    const dateCopy = new Date(date.getTime());
  
    const nextMonday = new Date(
      dateCopy.setDate(
        dateCopy.getDate() + ((7 - dateCopy.getDay() + 1) % 7 || 7),
      ),
    );
  
    return nextMonday;
  }

export function formattedDate(nonFormattedDate){
    const year = nonFormattedDate.getFullYear();
    const month = String(nonFormattedDate.getMonth() + 1).padStart(2, '0'); // Ajoute un zéro devant si le mois est inférieur à 10
    const day = String(nonFormattedDate.getDate()).padStart(2, '0'); // Ajoute un zéro devant si le jour est inférieur à 10
    
    const formattedDate = `${year}-${month}-${day}`;
    return formattedDate;
}
  
export function getNextDay(dayName, nextMonday) {
    // The current day
    let date = new Date(nextMonday);
    let now = date.getDay();

    // Days of the week
    let days = ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'];

    // The index for the day you want
    let day = days.indexOf(dayName.toLowerCase());

    // Special case for "lundi"
    if (day === 1 && now === 1) {
        // Return the current date (Monday of this week)
        return date;
    }

    // Find the difference between the current day and the one you want
    // If it's the same day as today (or a negative number), jump to the next week
    let diff = day - now;
    diff = diff < 1 ? 7 + diff : diff;

    // Get the timestamp for the desired day
    let nextDayTimestamp = date.getTime() + (1000 * 60 * 60 * 24 * diff);

    // Get the next day
    return new Date(nextDayTimestamp);
};

export function generateDateList(startDate) {
    // Convertir la date de départ en objet Date
    const date = new Date(startDate);
  
    // Créer un tableau vide pour stocker les dates
    const dateList = [];
  
    // Itérer sur les 7 derniers jours
    for (let i = 0; i < 7; i++) {
      // Obtenir le jour de la semaine
      const dayOfWeek = date.toLocaleDateString('fr-FR', { weekday: 'long' });
      // Obtenir le jour du mois
      const dayOfMonth = date.getDate();
      // Formater la date comme "jour de la semaine jj/mm"
      const formattedDate = `${dayOfWeek} ${dayOfMonth.toString().padStart(2, '0')}/${date.getMonth() + 1}`;
      // Ajouter la date formatée au tableau
      dateList.push(formattedDate);
  
      // Décaler la date d'un jour
      date.setDate(date.getDate() + 1);
    }
  
    // Retourner la liste des dates
    return dateList;
}


export function generateDateListAlternate(startDate) {
    // Convertir la date de départ en objet Date
    const date = new Date(startDate);
    console.log(date);
  
    // Créer un tableau vide pour stocker les dates
    const dateList = [];
  
    // Itérer sur les 7 derniers jours
    for (let i = 0; i < 7; i++) {
        // Obtenir le jour de la semaine
        const dayOfWeek = date.toLocaleDateString('fr-FR', { weekday: 'long' });
        // Obtenir le jour du mois
        const dayOfMonth = date.getDate();
        // Obtenir le mois
        const month = date.getMonth() + 1;
        // Formater la date comme "jour de la semaine jj/mm"
        const formattedDate = `${month < 10 ? '0' + month : month}-${dayOfMonth.toString().padStart(2, '0')}`;
        // Ajouter la date formatée au tableau
        dateList.push(formattedDate);
  
        // Décaler la date d'un jour
        date.setDate(date.getDate() + 1);
    }
  
    // Retourner la liste des dates
    return dateList;
}

export function generateDateListAlternate2(startDate) {
  // Convertir la date de départ en objet Date
  const date = new Date(startDate);
  console.log(date);

  // Créer un tableau vide pour stocker les dates
  const dateList = [];

  // Itérer sur les 7 derniers jours
  for (let i = 0; i < 7; i++) {
      // Obtenir le jour de la semaine
      const dayOfWeek = date.toLocaleDateString('fr-FR', { weekday: 'long' });
      // Obtenir le jour du mois
      const dayOfMonth = date.getDate();
      // Obtenir le mois
      const month = date.getMonth() + 1;
      const year = date.getFullYear();
      // Formater la date comme "jour de la semaine jj/mm"
      const formattedDate = `${year}-${month < 10 ? '0' + month : month}-${dayOfMonth.toString().padStart(2, '0')}`;
      // Ajouter la date formatée au tableau
      dateList.push(formattedDate);

      // Décaler la date d'un jour
      date.setDate(date.getDate() + 1);
  }

  // Retourner la liste des dates
  return dateList;
}

export function calculerDatefacture(date) {
  const dateActuelle = new Date(date);

  const jour = dateActuelle.getDate();
  const mois = dateActuelle.getMonth();
  const annee = dateActuelle.getFullYear();

  let nouvelleDate;

          if(jour< 10){
            nouvelleDate = new Date(annee, mois, 11);
          } else {
            nouvelleDate = new Date(annee, mois + 1, 11);
          }
          
  const nouvelleDateString = nouvelleDate.getFullYear() + '-' + (nouvelleDate.getMonth() + 1).toString().padStart(2, '0') + '-' + nouvelleDate.getDate().toString().padStart(2, '0');
  return nouvelleDateString;
}