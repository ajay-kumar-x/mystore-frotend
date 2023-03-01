function submitForm(event) {
        // Prevent the form from submitting automatically
        event.preventDefault();

        // Perform the desired operations

        var field1 = document.getElementById("mobile-number").value;
        var field2 = document.getElementById("cnf-mobile-number").value;
        if (field1 === field2) {
          // The values are the same

          // Get the object from local storage
          const products_list = JSON.parse(localStorage.getItem('products'));

          // Set the value of the hidden input field to the JSON string representation of the object
          document.getElementById('products').value = JSON.stringify(products_list);
        //now remove the product details from localStorage
        localStorage.removeItem("products");

          // Submit the form
          event.target.submit();

        } else {
          // The values are different
          alert("The values in the two fields are different.");
        }
      }