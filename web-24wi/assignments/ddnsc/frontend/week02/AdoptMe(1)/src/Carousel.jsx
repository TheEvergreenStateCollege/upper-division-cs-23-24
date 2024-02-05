// Class Components can't use Hooks

import { Component } from "react";

class Carousel extends Component {
  state = {
    active: 0,
  };

  static defaultProps = {
    images: ["https://pets-images.dev-apis.com/pets/none.jpg"],
  };

  handleIndexClick = (e) => {
    this.setState({
      active: +e.target.dataset.index,
    });
  };

  render() {
    const { active } = this.state,
      { images } = this.props;

    return (
      <div className={"carousel"}>
        <img src={images[active]} alt={"animal hero"} />
        <div className={"carousel-smaller"}>
          {images.map((photo, index) => (
            <img
              // eslint-disable-next-line
              onClick={this.handleIndexClick}
              data-index={index}
              key={photo}
              src={photo}
              className={index === active ? "active" : ""}
              alt={"animal thumbnail"}
            />
          ))}
        </div>
      </div>
    );
  }
}

export default Carousel;
