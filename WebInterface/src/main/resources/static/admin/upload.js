
function populateSubcategory() {
    var categoryElement = document.getElementById("category");
    var subcategoryElement = document.getElementById("subcategory");

    // clear previous options
    subcategoryElement.innerHTML = "";

    // get districts for selected state
    var selectedCategory = categoryElement.value;
    var subcategory = getSubcategory(selectedCategory);

    // add new options
    for (var i = 0; i < subcategory.length; i++) {
      var option = document.createElement("option");
      option.value = subcategory[i];
      option.text = subcategory[i];
      subcategoryElement.appendChild(option);
    }
  }


  function getSubcategory(category) {
    // this function would fetch the districts for the given state from a database or API
    // for the sake of example, we are returning static data
    var subcategory = [];
    if (category === "saree") {
      subcategory = ["cotton","silk","designer"];
    } else if (category === "jeans") {
      subcategory = ["skinny", "relaxed","cotton" ];
       }

    return subcategory;
  }

  function checkFile(input) {
    if (input.files[0].size > 1000000) { // limit to 1MB
      alert("The file size should be less than 1MB.");
      input.value = ""; // clear the input
    }
  }