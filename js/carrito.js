
document.addEventListener('DOMContentLoaded', function () {
    // Inicializar el carrito desde el almacenamiento local
    let cart = JSON.parse(localStorage.getItem('cart')) || [];

    const cartCountElement = document.getElementById('cart-count');
    const cartItemsElement = document.getElementById('cart-items');

    function addToCart(product) {
        cart.push(product);
        updateCartDisplay();
        saveCartToLocalStorage();
    }

    function updateCartDisplay() {
        cartCountElement.innerText = cart.length;
        cartItemsElement.innerHTML = '';

        cart.forEach((item, index) => {
            const itemElement = document.createElement('li');
            itemElement.className = 'list-group-item';
            itemElement.innerHTML = `
                ${item.name} - $${item.price}
                <button class="btn btn-sm btn-danger float-right" onclick="removeFromCart(${index})">&times;</button>
            `;
            cartItemsElement.appendChild(itemElement);
        });

        // Actualizar el total del carrito
        const totalPrice = cart.reduce((total, item) => total + parseFloat(item.price), 0);
        document.getElementById('cart-total').innerText = totalPrice.toFixed(2);
    }

    function removeFromCart(index) {
        cart.splice(index, 1);
        updateCartDisplay();
        saveCartToLocalStorage();
    }

    function saveCartToLocalStorage() {
        localStorage.setItem('cart', JSON.stringify(cart));
    }

    // Event listeners para los botones "Añadir al carrito"
    document.querySelectorAll('.btn-warning').forEach(button => {
        button.addEventListener('click', () => {
            const productElement = button.closest('.card');
            const product = {
                name: productElement.querySelector('.card-title').innerText,
                price: productElement.querySelector('.card-text').innerText.replace('$', '')
            };
            addToCart(product);
        });
    });

    // Mostrar el carrito al hacer clic en el ícono del carrito
    document.getElementById('cart').addEventListener('click', () => {
        updateCartDisplay();
        $('#cartModal').modal('show'); // Usar jQuery para mostrar el modal
    });

    // Cargar productos destacados dinámicamente
    const products = [

    ];

    const productListElement = document.getElementById('product-list');
    products.forEach(product => {
        const productCard = document.createElement('div');
        productCard.className = 'col-lg-3 col-md-4 col-sm-6 mb-4';
        productCard.innerHTML = `
            <div class="card">
                <img src="../Images/tira.jpeg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="card-text">$${product.price}</p>
                    <button class="btn btn-warning btn-sm">Añadir al carrito</button>
                </div>
            </div>
        `;
        productListElement.appendChild(productCard);
    });

    // Event listener para eliminar productos del carrito
    // Necesitamos delegación de eventos debido a los elementos dinámicos
    cartItemsElement.addEventListener('click', function (event) {
        if (event.target.classList.contains('btn-danger')) {
            const index = event.target.parentNode.dataset.index; // Obtener el índice del elemento
            removeFromCart(index);
        }
    });
});
document.addEventListener('DOMContentLoaded', function () {
    const cartSummaryElement = document.getElementById('cart-summary');

    function loadCartItems() {
        // Obtener los productos del carrito desde el almacenamiento local
        let cart = JSON.parse(localStorage.getItem('cart')) || [];

        // Limpiar el contenedor del resumen del carrito
        cartSummaryElement.innerHTML = '';

        // Si el carrito está vacío, mostrar un mensaje indicando que está vacío
        if (cart.length === 0) {
            cartSummaryElement.innerHTML = '<p>El carrito está vacío.</p>';
            return;
        }

        // Crear una tabla para mostrar los productos del carrito
        const table = document.createElement('table');
        table.className = 'table';
        table.innerHTML = `
            <thead>
                <tr>
                    <th>Producto</th>
                    <th>Precio</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí se cargarán dinámicamente los productos del carrito -->
            </tbody>
        `;

        // Iterar sobre los productos del carrito y añadir filas a la tabla
        const tbody = table.querySelector('tbody');
        cart.forEach(product => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${product.name}</td>
                <td>$${product.price}</td>
            `;
            tbody.appendChild(row);
        });

        // Agregar la tabla al contenedor del resumen del carrito
        cartSummaryElement.appendChild(table);
    }

    // Cargar los productos del carrito al cargar la página
    loadCartItems();

    // Manejar la subida de archivos
    const uploadForm = document.getElementById('uploadForm');
    uploadForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevenir el envío del formulario por defecto

        const formData = new FormData(uploadForm);
        const fileUpload = formData.get('fileUpload');

        // Aquí podrías manejar la carga del archivo, por ejemplo enviarlo a un servidor, etc.
        // En este caso, simplemente mostramos un mensaje en consola
        if (fileUpload) {
            console.log('Archivo seleccionado:', fileUpload.name);
            // Aquí podrías añadir lógica adicional para enviar el archivo a un servidor, etc.
        } else {
            console.log('No se seleccionó ningún archivo.');
        }
    });
});
