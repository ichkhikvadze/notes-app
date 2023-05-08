<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
            crossorigin="anonymous"></script>
</head>
<body>
<section class="container-fluid">
    <div class="row justify-content-center py-5">
        <div class="col-6 rounded-3 my-form-container">
            <%if (request.getAttribute("error") != null) {%>
            <h3 class="text-center text-warning"><%= request.getAttribute("error")%>
            </h3>
            <%}%>
            <h3 class="text-center my-5 text-secondary">Log In</h3>
            <form class="px-md-2" action="login" method="post">
                <div class="form-outline mb-4">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" id="username" class="form-control"
                           placeholder="Username"/>
                </div>
                <div class="form-outline mb-4">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" id="password" class="form-control"
                           placeholder="Password"/>
                </div>
                <div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success btn-lg mb-1">Log In</button>
                    </div>
                    <div class="text-center my-2">
                        <p>Dont have an account? <a href="/signup">Sign Up</a></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
