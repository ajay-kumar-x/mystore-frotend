
//.............order Submit form............
function submitForm(event) {
        // Prevent the form from submitting automatically
        event.preventDefault();

        // Perform the desired operations like checking mobile number

        var mobile_number = document.getElementById("mobile-number").value;
        var cnf_mobile_number = document.getElementById("cnf-mobile-number").value;
        if (mobile_number === cnf_mobile_number) {
             // The values are the same

             // Get the object from local storage
              const products_list = JSON.parse(localStorage.getItem('products'));

             // Set the value of the hidden input field to the JSON string representation of the object
             document.getElementById('products-hidden').value = JSON.stringify(products_list);

             //setting the total price in the hidden input field
            document.getElementById('total-price-hidden').value = parseFloat(document.getElementById('total-price').textContent.split('₹')[1]);
            //now remove the product details from localStorage
             localStorage.setItem("products", "[]");
             localStorage.setItem("mobile_number", mobile_number);

          // Submit the form
          event.target.submit();

        } else {
          // The values are different
          alert("Mobile number not matching.");
        }
      }