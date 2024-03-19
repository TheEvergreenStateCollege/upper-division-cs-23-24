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
    const { active } = this.state;
    const { images } = this.props;

    return (
      <div className={"mb-10 flex items-center justify-center"}>
        <img src={images[active]} alt={"animal hero"} className={"mr-4"} />
        <div className={"flex flex-col "}>
          {images.map((photo, index) => (
            <img
              // eslint-disable-next-line
              onClick={this.handleIndexClick}
              data-index={index}
              key={photo}
              src={photo}
              className={`mr-2 h-16 w-16 cursor-pointer rounded-full 
              ${index === active ? "border-2 border-orange-500" : ""}`}
              alt={"animal thumbnail"}
            />
          ))}
        </div>
      </div>
    );
  }
}

export default Carousel;
