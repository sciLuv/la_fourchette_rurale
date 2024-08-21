let member = JSON.parse(localStorage.getItem("member"));
import fetchDeliveryDatesForMember from "../controllers/daysforMember.js"
fetchDeliveryDatesForMember(member.id)