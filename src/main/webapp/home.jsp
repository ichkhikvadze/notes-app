<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container">
        <div>
            <ul class="navbar-nav">
                <li class="nav-item"><%= session.getAttribute("username")%>
                </li>
            </ul>
        </div>
        <div class="justify-content-end align-center">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="#my-notes">My Notes</a></li>
                <li class="nav-item"><a class="nav-link" href="#create-note">Create Note</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>
<section class="my-2 py-3">
    <section class="container-fluid pb-5 bg-dark">
        <div class="row justify-content-center">
            <div class="col-6 rounded-3 my-form-container">
                <h3 class="text-center my-5 text-secondary">Create Note</h3>
                <form action="/create-note" method="post" class="px-md-2">
                    <textarea class="form-control" name="note-text" rows="5"></textarea>
                    <div class="text-center my-3">
                        <button class="btn btn-success">Add Note</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <section id="my-notes" class="container bg-light py-4">
        <h3 class="text-center">My Notes</h3>
        <div class="row">
            <c:forEach var="note" items="${sessionScope.userNotes}" varStatus="loop">
                <div class="col-lg-4 col-md-6 col-sm-10 my-3">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <p class="card-text">${note.noteText}</p>
                            <div class="text-center my-3">
                                <form action="/delete-note/${note.noteId}" method="post">
                                    <button class="btn btn-danger">Delete</button>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</section>
</body>
</html>
