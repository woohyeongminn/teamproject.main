function App() {

    return (
      <React.Fragment>
        <ConnectedRouter history={history}>
          <Route path="/signup" exact component={Signup} />
          <Route path="/login" exact component={Login} />
          
          <Route path="/ogong/app/login" component={OAuth2RedirectHandler}></Route>
          
        </ConnectedRouter>
      </React.Fragment>
    );
  }