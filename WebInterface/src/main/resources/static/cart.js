 // Retrieve the array of products from local storage
  let products = JSON.parse(localStorage.getItem("products"));

  // Get a reference to the HTML element where you want to render the product list
  let productList = document.getElementById("product-list");

  // Loop through the array of products and generate HTML elements for each product
  for (let i = 0; i < products.length; i++) {
    let product = products[i];

    // Create a container for the product information
    let productContainer = document.createElement("div");
    productContainer.classList.add("product-container");

    // Create an image element for the product image
    let image = document.createElement("img");
    image.src = product_service_url+'/images/'+product.primary_image;
    image.alt = product.description;
    image.classList.add("product-image");

    // Create a container for the product details
    let detailsContainer = document.createElement("div");
    detailsContainer.classList.add("product-details");

    // Add the product description
    let description = document.createElement("p");
    description.textContent = product.description;
    description.classList.add("product-description");
    detailsContainer.appendChild(description);

    // Add the product price
    let price = document.createElement("p");
    price.textContent = "Price: ₹" + product.price;
    price.classList.add("product-price");
    detailsContainer.appendChild(price);

    // Add the product quantity
    let quantity = document.createElement("p");
    quantity.textContent = "Quantity: " + product.quantity;
    quantity.classList.add("product-quantity");
    detailsContainer.appendChild(quantity);

    // Add the product size
    let size = document.createElement("p");
    size.textContent = "Size: " + product.size;
    size.classList.add("product-size");
    detailsContainer.appendChild(size);

 // Create a button to remove the product from the list
    let removeButton = document.createElement("button");
    removeButton.textContent = "Remove";
    removeButton.classList.add("remove-button");
    removeButton.addEventListener("click", function() {
          // Remove the product container from the product list container
          productList.removeChild(productContainer);
          // Get a reference to the cart icon element
          let cartIcon = document.querySelector(".fa-stack");

          // Remove the product from the array and update local storage
          if (products.length == 1) {
              localStorage.setItem("products", "[]");
               // Recalculate the total price and update the display
               total.textContent = "Cart is empty";
               // Update the cart icon element to display the current number of items
                cartIcon.setAttribute("data-count", 0);
            } else {
              products.splice(i,1);
              localStorage.setItem("products", JSON.stringify(products));
               // Recalculate the total price and update the display
               total.textContent = "Total price: ₹" + calculateTotalPrice(products);
               // Update the cart icon element to display the current number of items
               cartIcon.setAttribute("data-count", products.length);
            }
        });

    // Add the image and details containers to the product container
    productContainer.appendChild(image);
    productContainer.appendChild(detailsContainer);
    productContainer.appendChild(removeButton);

    // Add the product container to the product list container
    productList.appendChild(productContainer);
  }


   // Create a function to calculate the total price of the products
    function calculateTotalPrice(products) {

      let totalPrice = 0;
      for (let i = 0; i < products.length; i++) {
        let product = products[i];
        totalPrice += product.quantity * product.price;
      }
      return totalPrice;
    }

    // Get a reference to the HTML element where you want to display the total price
    let total = document.getElementById("total-price");

    // Display the total price
    if (products.length > 0){
    total.textContent = "Total price: ₹" + calculateTotalPrice(products);
    }else{
    total.textContent = "Cart is empty";
    }
