import React from 'react';
import HowOftenDropdown from './HowOftenDropdown';
class Input extends React.Component {
    constructor(props) {
      super(props);
      this.state = {title: '', amount: 0, description: '', todayMS: null, howOften: 0 };
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
      console.log(this.state);
    }
    componentDidMount() {
      var time = new Date().getTime();
      this.setState({todayMS: time});
      fetch('http://localhost:8080/testSQLServer3/rest/services/getToday', {
        method: "GET"
      }).then(response => response.json())
      .then(todayMS => this.setState({todayMS}));
      console.log(this.state);
    }
    handleChange(i, event) {
        let values = [...this.state.values];
        values[i] = event.target.value;
        this.setState({ values });
    }
    handleTitleChange(event) {
      let aTitle = event.target.value;
      this.setState({title: aTitle});
    }
    handleAmountChange(event) {
      let aAmount = event.target.value;
      this.setState({amount: aAmount});
    }
    handleDescriptionChange(event) {
      let aDesc = event.target.value;
      this.setState({description: aDesc});
    }
    handleHowOftenChange(event) {
      let aHowOften = event.target.value;
      this.setState({howOften: aHowOften});
    }
    handleSubmit(event) {
        event.preventDefault();
        alert('Values submitted: ' + this.state.values);
        const data = new FormData(event.target);
        console.log(data);
        console.log(this.state)
        console.log(JSON.stringify(this.state))
        fetch("http://localhost:8080/testSQLServer3/rest/services/add", {
          method: "POST",
          body: JSON.stringify(this.state),
          //body: data,
          headers: {
            "Content-Type": "application/json"
          },
        }).then(function(response) {
          return response.text()
        })      
      }
    todayDate() {
      var ms = this.state.todayMS;
      var date = new Date(ms);
      return date.toString();
    }
    render() {
      console.log("123");
      console.log(this.state);
      return (
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Enter Title: </label>
            <input type="text" value={this.state.title||' '} onChange={this.handleTitleChange.bind(this)} />
          </div>
          <div>
            <label>Enter Amount (number): </label>
            <input type="text" value={this.state.amount||' '} onChange={this.handleAmountChange.bind(this)} />
          </div>
          <div>
            <label>Enter Description: </label>
            <input type="text" value={this.state.description||' '} onChange={this.handleDescriptionChange.bind(this)} />
          </div>
          <div>
            <label>Current Time: {this.todayDate()}</label>
          </div>
          <div>
            <HowOftenDropdown onChange={this.handleHowOftenChange}/>
          </div>
        <input type="submit" value="Enter Transaction" />
        </form> 
      );
    }
  }

export default Input;