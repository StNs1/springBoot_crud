
$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/rest',
        dataType: "json",
        success: function (list) {
            for (let i = 0; i < list.length; i++) {
                addUserInTable(list[i]);
            }
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
    let roleName
    if ($("#optionInputDelete").val() == 1) {
        roleName = 'ROLE_USER';
    } else {
        roleName = 'ROLE_ADMIN'
    }
    let deleteUser = {
        id: $("#idInputDelete").val(),
        email: $("#emailInputDelete").val(),
        password: $("#passwordInputDelete").val(),
        roles: [{
            id: $("#optionInputDelete").val(),
            name: roleName
        }]
    }
    $.ajax({
        type: 'DELETE',
        url: '/rest/admin/delete',
        data: JSON.stringify(deleteUser),
        contentType: "application/json",
        dataType: "json",
        success: function (list) {
            $("#tbody").children().remove();
            for (let i = 0; i < list.length; i++) {
                addUserInTable(list[i]);
            }
        }
    })
    $("#deleteModal").modal('hide');
}

function getUserDelete(id) {
    let userId = {
        id: id
    }
    $.ajax({
        type: 'POST',
        url: '/rest/admin/delete',
        data: JSON.stringify(userId),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (user) {
            $("#selectInputDelete").children().remove();
            $("#idInputDelete").val(user.id);
            $("#emailInputDelete").val(user.email);
            $("#passwordInputDelete").val(user.password);
            for(let role in user.roles) {
                $("#selectInputDelete").append(`<option id="optionInputDelete" value="${user.roles[role].id}" label="${user.roles[role].name}"></option>`)
            }
        }
    })
}

function getUser(id) {
    let userId = {
        id: id
    }
    $.ajax({
        type: 'POST',
        url: '/rest/admin/edit',
        data: JSON.stringify(userId),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (user) {
            $("#selectInput").children().remove();
            $("#idInput").val(user.id);
            $("#emailInput").val(user.email);
            $("#passwordInput").val(user.password);
            for(let role in user.roles) {
                $("#selectInput").append(`<option id="optionInput" value="${user.roles[role].id}" label="${user.roles[role].name}"></option>`)
            }
        }
    })
}

function editUser() {
    let roleName
    if ($("#selectInput").val() == 1) {
        roleName = 'ROLE_USER';
    } else {
        roleName = 'ROLE_ADMIN'
    }
    let editUser = {
        id: $("#idInput").val(),
        email: $("#emailInput").val(),
        password: $("#passwordInput").val(),
        roles: [{
            id: $("#optionInput").val(),
            name: roleName
        }]
    }
    $.ajax({
        url: '/rest/admin/edit',
        type: 'PUT',
        data: JSON.stringify(editUser),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (list) {
            $("#tbody").children().remove();
            for (let i = 0; i < list.length; i++) {
                addUserInTable(list[i]);
            }
        }
    })
    $("#editModal").modal('hide');
}

function addUserInTable(list) {
    let tbody = $("#tbody");
    tbody.append(`<tr>`);
    tbody.append(`<td id="id+${list.id}">${list.id}</td>`);
    tbody.append(`<td id="email+${list.id}">${list.email}</td>`);
    tbody.append(`<td id="password+${list.id}">${list.password}</td>`);
    let roleTd = `<td id="role+${list.id}">`;
    for (let role in list.roles) {
        roleTd += list.roles[role].name;
    }
    tbody.append(roleTd + "</td>");
    tbody.append(`<td><button type="button" class="btn btn-primary" value="${list.id}" id="editCallModal+${list.id.toString()}" name="editCallModal" 
                    data-toggle="modal" onclick="getUser(${list.id})" data-target="#editModal">Edit</button></td>`);
    tbody.append(`<td><button type="button" class="btn btn-danger" value="${list.id}" id="deleteCallModal+${list.id.toString()}" name="deleteCallModal" 
                    data-toggle="modal" onclick="getUserDelete(${list.id})" data-target="#deleteModal">Delete</button></td>`);
    tbody.append(`</tr>`);
}

