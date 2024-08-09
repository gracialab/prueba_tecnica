let app_books = {
    backend: 'http://127.0.0.1:5001/api/v1',
    table: null,
    init: function() {
        // Start the table directly with the URL that gets all the books
        app_books.initDatatable('#books', app_books.backend + '/books');
    },
    initDatatable: function(id, url) {
        app_books.table = $(id).DataTable({
            language: {
                "decimal": "",
                "emptyTable": "No hay información",
                "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                "infoPostFix": "",
                "thousands": ",",
                "lengthMenu": "Mostrar _MENU_ Entradas",
                "loadingRecords": "Cargando...",
                "processing": "Procesando...",
                "search": "Buscar:",
                "zeroRecords": "Sin resultados encontrados",
                "paginate": {
                    "first": "Primero",
                    "last": "Último",
                    "next": "Siguiente",
                    "previous": "Anterior"
                }
            },
            ajax: {
                url: url,
                dataSrc: function(json) {
                    return json;
                }
            },
            dom: 'Bfrtip',
            columns: [
                {
                    data: null,
                    "render": function(data, type, full, meta) {
                        return meta.row + 1;
                    }
                },
                {data: 'name'},
                {data: 'description'}
            ],
            buttons: []
        });
    }
};

$(document).ready(function() {
    app_books.init();
});
