$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/rest',
        dataType:"json",
        success: function (list) {
            let $tbody = $("#tbody");
            for(let i = 0; i < list.length; i++) {
                $tbody.append("<tr>");
                $tbody.append(`<td>${list[i].id}</td>`);
                $tbody.append(`<td>${list[i].email}</td>`);
                $tbody.append(`<td>${list[i].password}</td>`);
                let roleTd = `<td>`;
                for(let role in list[i].roles) {
                    roleTd += list[i].roles[role].name;
                }
                $tbody.append(roleTd + "</td>");
                $tbody.append(`<td><button type="button" class="btn btn-primary" data-toggle="modal">Edit</button></td>`)
                $tbody.append(`<td><button type="button" class="btn btn-danger" data-toggle="modal">Delete</button></td>`)
                $tbody.append("</tr>");
            }
        }
    })
})