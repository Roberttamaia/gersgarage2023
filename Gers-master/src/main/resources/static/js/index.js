const preloader = document.querySelector('.preloader')
const newType = document.querySelector(".newType");
const addType = document.querySelector(".addType");
const newSale = document.querySelector(".newSale");
const addSale = document.querySelector(".addSale");
window.addEventListener('DOMContentLoaded', () => {
    preloader.classList.add('loaded')

});
if (newType){
newType.addEventListener('click', () =>{
addType.style.display='Block';
});
}
if (newSale){
newSale.addEventListener('click', () =>{
addSale.style.display='Block';
});
}

  $(function() {

$("#myTable").DataTable({
    select: true,
     pageLength : 5,

});
  setTimeout(function () {
   // Closing the alert
   $('.alert').alert('close');
 }, 3000);


});
