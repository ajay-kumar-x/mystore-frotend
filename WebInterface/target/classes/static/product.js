function addToCart(product,str){
    //alertBox will not be shown if clicked on buyNow
            if(str != "buyNow"){
            document.getElementById('alertBoxOnAddingToCart').style.display='block';
            }
            // Get the size or quantity select elements

            const item={id:product['id'],category:product['category'],description:product['description'], price:product['price'],primary_image:product['primary_image']};

              if(document.getElementById('size')==null){
                  item.size="all";
                  item.quantity=document.getElementById('quantity').value;
               }else{
                    item.quantity=1;
                     item.size=document.getElementById('size').value;
               }
               productArray= JSON.parse(localStorage.getItem('products'));
               if(productArray == null)  productArray=[];
               productArray.push(item);

               console.log(productArray);
             localStorage.setItem("products", JSON.stringify(productArray));

              // var user = JSON.parse(localStorage.getItem('user'));

}