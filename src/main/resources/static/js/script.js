document.addEventListener('DOMContentLoaded', () => {
    const coffeeTableBody = document.getElementById('coffee-table-body');
    const coffeeForm = document.getElementById('coffee-form');
    const coffeeIdInput = document.getElementById('coffee-id');
    const nameInput = document.getElementById('name');
    const descriptionInput = document.getElementById('description');
    const priceInput = document.getElementById('price');

    let isEditMode = false;

    // Function to refresh the coffee list
    const refreshCoffeeList = () => {
        fetch('/products', {
            headers: { 'Accept': 'application/json' }
        })
        .then(response => response.json())
        .then(coffees => {
            coffeeTableBody.innerHTML = '';
            coffees.forEach(coffee => {
                const row = coffeeTableBody.insertRow();
                row.insertCell().textContent = coffee.id;
                row.insertCell().textContent = coffee.name;
                row.insertCell().textContent = coffee.description;
                row.insertCell().textContent = '$' + coffee.price.toFixed(2);
                
                const actionsCell = row.insertCell();
                
                const editButton = document.createElement('button');
                editButton.textContent = 'Edit';
                editButton.classList.add('edit-btn');
                editButton.dataset.coffeeId = coffee.id;
                editButton.addEventListener('click', () => populateFormForEdit(coffee));
                actionsCell.appendChild(editButton);

                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Delete';
                deleteButton.classList.add('delete-btn');
                deleteButton.dataset.coffeeId = coffee.id;
                deleteButton.addEventListener('click', () => deleteCoffee(coffee.id));
                actionsCell.appendChild(deleteButton);
            });
        })
        .catch(error => console.error('Error fetching coffees:', error));
    };

    // Initial load
    refreshCoffeeList();

    // Form submission handler
    coffeeForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const formData = new FormData(coffeeForm);
        const coffeeData = {
            name: formData.get('name'),
            description: formData.get('description'),
            price: parseFloat(formData.get('price'))
        };

        const method = isEditMode ? 'PUT' : 'POST';
        const url = isEditMode ? `/products/${formData.get('id')}` : '/products';

        fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(coffeeData)
        })
        .then(response => {
            if (response.ok) {
                coffeeForm.reset();
                refreshCoffeeList();
                isEditMode = false;
                coffeeIdInput.value = '';
                showMessage('Coffee saved successfully!', 'success');
            } else {
                showMessage('Error saving coffee', 'error');
            }
        })
        .catch(error => {
            console.error('Error saving coffee:', error);
            showMessage('Error saving coffee: ' + error.message, 'error');
        });
    });

    // Populate form for editing
    const populateFormForEdit = (coffee) => {
        isEditMode = true;
        coffeeIdInput.value = coffee.id;
        nameInput.value = coffee.name;
        descriptionInput.value = coffee.description;
        priceInput.value = coffee.price;
    };

    // Delete coffee
    const deleteCoffee = (id) => {
        if (confirm('Are you sure you want to delete this coffee?')) {
            fetch(`/products/${id}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    refreshCoffeeList();
                    showMessage('Coffee deleted successfully!', 'success');
                } else {
                    showMessage('Error deleting coffee', 'error');
                }
            })
            .catch(error => {
                console.error('Error deleting coffee:', error);
                showMessage('Error deleting coffee: ' + error.message, 'error');
            });
        }
    };

    // Show message
    const showMessage = (message, type) => {
        const messageDiv = document.getElementById('form-message');
        messageDiv.textContent = message;
        messageDiv.className = `message ${type}`;
        messageDiv.style.display = 'block';
        
        setTimeout(() => {
            messageDiv.style.display = 'none';
        }, 3000);
    };
});