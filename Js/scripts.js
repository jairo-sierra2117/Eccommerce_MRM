document.addEventListener('DOMContentLoaded', function () {
    const modal = new bootstrap.Modal(document.getElementById('editModal'));
    const form = document.querySelector('#editForm');
    let currentCard = null;

    function cargarEmpleados() {
        const empleados = JSON.parse(localStorage.getItem('empleados')) || [];
        const contenedor = document.querySelector('.container .row');
        contenedor.innerHTML = ''; // Limpiar el contenedor antes de cargar empleados

        empleados.forEach(empleado => {
            const card = document.createElement('div');
            card.classList.add('col-md-4');
            card.innerHTML = `
                <div class="card mb-3">
                    <div class="card-header">${empleado.nombre}</div>
                    <div class="card-body">
                        <p>Rol: ${empleado.rol}</p>
                        <i class="fa fa-info-circle" data-bs-toggle="tooltip" title="
                            Celular: ${empleado.numeroCelular}<br>
                            Email: ${empleado.email}<br>
                            Cédula: ${empleado.cedula}<br>
                            Contraseña: ${empleado.contraseña}
                        "></i>
                        <div class="d-flex justify-content-between mt-3">
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal" 
                                data-name="${empleado.nombre}" data-role="${empleado.rol}" data-celular="${empleado.numeroCelular}" 
                                data-email="${empleado.email}" data-cedula="${empleado.cedula}" data-contraseña="${empleado.contraseña}">
                                Editar
                            </button>
                            <a href="#" class="btn btn-danger">Eliminar</a>
                        </div>
                    </div>
                </div>
            `;
            contenedor.appendChild(card);
        });

        // Inicializar tooltips
        var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl, { html: true });
        });

        // Asignar eventos de clic a los botones de editar y eliminar
        document.querySelectorAll('.btn-primary[data-bs-toggle="modal"]').forEach(button => {
            button.addEventListener('click', function (event) {
                currentCard = event.target.closest('.card');
                const name = event.target.getAttribute('data-name');
                const role = event.target.getAttribute('data-role');
                const celular = event.target.getAttribute('data-celular');
                const email = event.target.getAttribute('data-email');
                const cedula = event.target.getAttribute('data-cedula');
                const contraseña = event.target.getAttribute('data-contraseña');

                form.nombre.value = name;
                form.role.value = role;
                form.numeroCelular.value = celular;
                form.email.value = email;
                form.cedula.value = cedula;
                form.contraseña.value = contraseña;
                form.originalName.value = name;

                modal.show();
            });
        });

        document.querySelectorAll('.btn-danger').forEach(button => {
            button.removeEventListener('click', eliminarEmpleado);
            button.addEventListener('click', eliminarEmpleado);
        });
    }

    function eliminarEmpleado(event) {
        if (confirm('¿Estás seguro de que deseas eliminar este colaborador?')) {
            const card = event.target.closest('.card');
            const nombreEmpleado = card.querySelector('.card-header').innerText;

            // Eliminar la tarjeta del DOM
            card.remove();

            // Eliminar el empleado del almacenamiento local
            let empleados = JSON.parse(localStorage.getItem('empleados')) || [];
            empleados = empleados.filter(emp => emp.nombre !== nombreEmpleado);
            localStorage.setItem('empleados', JSON.stringify(empleados));

            // Volver a cargar empleados para refrescar la vista
            cargarEmpleados();
        }
    }

    // Cargar empleados almacenados al cargar la página
    cargarEmpleados();

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const name = form.nombre.value;
        const role = form.role.value;
        const celular = form.numeroCelular.value;
        const email = form.email.value;
        const cedula = form.cedula.value;
        const contraseña = form.contraseña.value;

        // Verifica si es una edición o un nuevo colaborador
        let empleados = JSON.parse(localStorage.getItem('empleados')) || [];
        const originalName = form.originalName.value;

        if (originalName) {
            empleados = empleados.map(emp => emp.nombre === originalName 
                ? { ...emp, nombre: name, rol: role, numeroCelular: celular, email: email, cedula: cedula, contraseña: contraseña } 
                : emp);
        } else {
            empleados.push({ nombre: name, rol: role, numeroCelular: celular, email: email, cedula: cedula, contraseña: contraseña });
        }

        localStorage.setItem('empleados', JSON.stringify(empleados));

        modal.hide();

        // Eliminar cualquier overlay restante
        document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());

        cargarEmpleados(); // Recargar los empleados para refrescar los datos
    });
});

function goToPreviousPage() {
        // Lógica para ir a la página anterior
        alert("Ir a la página anterior");
        }

function goToNextPage() {
        // Lógica para ir a la siguiente página
        alert("Ir a la siguiente página");
        }
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
            cartItemsElement.addEventListener('click', function(event) {
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
        