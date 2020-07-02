$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/rest',
        dataType: "json",
        success: function (list) {
            let tbody = $("#tbody");
            for (let i = 0; i < list.length; i++) {
                tbody.append(`<tr>`);
                tbody.append(`<td>${list[i].id}</td>`);
                tbody.append(`<td>${list[i].email}</td>`);
                tbody.append(`<td>${list[i].password}</td>`);
                let roleTd = `<td>`;
                for (let role in list[i].roles) {
                    roleTd += list[i].roles[role].name;
                }
                tbody.append(roleTd + "</td>");
                tbody.append(`<td><button type="button" class="btn btn-primary" value="${list[i].id}" name="editModal" 
                    data-toggle="modal">Edit</button></td>`);
                tbody.append(`<td><button type="button" class="btn btn-danger" value="${list[i].id}" name="deleteModal" 
                    data-toggle="modal">Delete</button></td>`);
                tbody.append(`</tr>`);
            }
        }
    })
});

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: '/rest/admin/delete',
        dataType: "JSON",
        success: function (user) {

        }
    })
})

function addUser() {
    console.log($('#fromAddUser').serializeArray());
   /*let roleName;
    if ($("#addRoles").val() === 1) {
        roleName = 'ROLE_USER';
    } else {
        roleName = 'ROLE_ADMIN'
    }
    let dataFromForm = {
        email: $("#addEmail").val(),
        password: $("#addPassword").val(),
        roles: {
            id: $("#addRoles").val(),
            name: roleName
        }
    }*/
    let dataFromForm = {
        email: $("#addEmail").val(),
        password: $("#addPassword").val(),
        roles: $("#addRoles").val()
    }

    console.log(dataFromForm);

    $.ajax({
        type: 'POST',
        url: '/rest/admin/add',
        data: JSON.stringify(dataFromForm),
        //     rolesIds: JSON.stringify(roleIds)
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            $('#result').html(data);
        }
    })
}

/*function addUser() {
    $.ajax("/rest/admin/add", "POST", {
        data: {
            email: $("#addEmail").val(),
            password: $("#addPassword").val(),
            roles: $("#addRoles").val()
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            $(".result").html(data);
        }
    })
}*/