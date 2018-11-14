import React from 'react';
import HowOftenDropdown from './HowOftenDropdown';
class SummaryReport  extends React.Component {
    constructor(props) {
      super(props);
      this.state = {};
    }
    componentDidMount() {
    }
    renderTable() {
        fetch("http://localhost:8080/testSQLServer3/rest/services/showFullTable", {
          method: "POST",
          //body: data,
          headers: {
            "Content-Type": "application/json"
          },
        }).then(function(response) { 
          return response.text()
        })
      }
    todayDate() {
      var today = new Date().getTime();
      return today.toString();
    }
    render() {
      return (
        <div>
            <label>Current Time: {this.todayDate()}</label>
        </div>
      );
    }
  }
export default SummaryReport;