let buttonEditUser;
let buttonDeleteUser;

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/rest',
        dataType: "json",
        success: function (list) {
            for (let i = 0; i < list.length; i++) {
                addUserInTable(list[i]);
            }
            bindButtonEditDelete();
        }
    })
});

function addUser() {
    let roleName
    if ($("#addRoles").val() == 1) {
        roleName = 'ROLE_USER';
    } else {
        roleName = 'ROLE_ADMIN'
    }
    let dataFromForm = {
        email: $("#addEmail").val(),
        password: $("#addPassword").val(),
        roles: [{
            id: $("#addRoles").val(),
            name: roleName
        }]
    }
    $.ajax({
        type: 'POST',
        url: '/rest/admin/add',
        data: JSON.stringify(dataFromForm),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            if (data.id != 0) {
                addUserInTable(data)
            } else {
                alert("Введите данные")
            }
            $("#addEmail").val("");
            $("#addPassword").val("");
            $("#addRoles").val("1");
        }
    })
}

function deleteUser() {

}

function bindButtonEditDelete() {
    buttonEditUser = $("[name=editCallModal]");
    buttonEditUser.click(
        function () {
            //fillModalFields("edit", getUser(parseInt($(this).attr("value"))));
            $("#modalEdit").modal('show');
        }
    )
    buttonDeleteUser = $("[name=deleteCallModal]");
    buttonDeleteUser.click(function () {
        //fillModalFields("delete", getUser(parseInt($(this).attr("value"))));
        $("#modalDelete").modal('show');
    })
}

function addUserInTable(list) {
    let tbody = $("#tbody");
    tbody.append(`<tr>`);
    tbody.append(`<td>${list.id}</td>`);
    tbody.append(`<td>${list.email}</td>`);
    tbody.append(`<td>${list.password}</td>`);
    let roleTd = `<td>`;
    for (let role in list.roles) {
        roleTd += list.roles[role].name;
    }
    tbody.append(roleTd + "</td>");
    tbody.append(`<td><button type="button" class="btn btn-primary" value="${list.id}" name="editCallModal" 
                    data-toggle="modal" data-target="#editModal">Edit</button></td>`);
    tbody.append(`<td><button type="button" class="btn btn-danger" value="${list.id}" name="deleteCallModal" 
                    data-toggle="modal" data-target="#deleteModal">Delete</button></td>`);
    tbody.append(`</tr>`);
}

