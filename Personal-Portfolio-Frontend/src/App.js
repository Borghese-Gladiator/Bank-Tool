import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

import Input from './Input';

import Tabs from './Tabs';
require('./styles.css');

class App extends Component {
  render() {
    return (
      <div>
        <h1>Personal Portfolio</h1>
       <Tabs>
        <div label="Make A Transaction" style={{marginRight: '10px'}}>
          <Input/> 
          See ya later, <em>Alligator</em>!
        </div>
        <div label="Summary Report">
          {/*<SummaryReport/>*/}
          After 'while, <em>Crocodile</em>!
        </div>
        <div label="Transaction History">
          Nothing to see here, this tab is <em>extinct</em>!
        </div>
      </Tabs>
      </div>
    );
  }
}

export default App;
