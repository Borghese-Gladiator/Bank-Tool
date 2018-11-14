import React from 'react';
import PropTypes from 'prop-types';
class HowOftenDropdown extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      displayMenu: false,
      data: 0
    };
    this.showDropdownMenu = this.showDropdownMenu.bind(this);
    this.hideDropdownMenu = this.hideDropdownMenu.bind(this);
  
  };
  
  showDropdownMenu(event) {
      event.preventDefault();
      this.setState({ displayMenu: true }, () => {
      document.addEventListener('click', this.hideDropdownMenu);
      });
    }
  
    hideDropdownMenu() {
      this.setState({ displayMenu: false }, () => {
        document.removeEventListener('click', this.hideDropdownMenu);
      });
  
    }
    handleDataChange(event) {
      let someData = event.target.value;
      this.setState({data: someData});
    }

    changeDataToOne() {
      this.setState({data: 1});
    }
    render() {
      console.log(this.state)
      return (
          <div  className="dropdown" style = {{background:"red",width:"200px"}} >
           <div className="button" onClick={this.showDropdownMenu}> How Often </div>
  
            { this.state.displayMenu ? (
            <ul>
           <li><a className="active" href="#One-Time" onClick={() => this.setState({data: 0})}>One-Time</a></li>
           <li><a href="#Daily" onClick={this.changeDataToOne}>Daily</a></li>
           <li><a href="#Weekly" onClick={this.handleDataChange}>Weekly</a></li>
           <li><a href="#Monthly"onClick={this.handleDataChange}>Monthly</a></li>
           <li><a href="#Yearly"onClick={this.handleDataChange}>Yearly</a></li>
            </ul>
          ):
          (
            null
          )
          } 
         </div>
  
      );
    }
}
export default HowOftenDropdown;