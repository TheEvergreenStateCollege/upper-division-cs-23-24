import { Component } from "react";

class Carousel extends Component {
    state = {
        active: 0,
    };

    static defaultProps = {
        images: ["http://pets-images.dev-apis.com/pets/none.jpg"],
    };

    handleIndexClick = (event) => {
        this.setState({
            active: +event.target.dataset.index,
        });
    };

    render() {
        const { active } = this.state;
        const { images } = this.props;
        return (
            <div className="flex justify-around items-center h-[400px] mt-2">
                <img className="max-w-[45%] max-h-[400px]" src={images[active]} alt="animal" />
                <div className="w-6/12">
                    {images.map((photo, index) => (
                        // eslint-disable-next-line
                        <img
                            key={photo}
                            src={photo}
                            className={index === active ? "opacity-60 border-[#333] w-[100px] h-[100px] inline-block cursor-pointer m-[15px] rounded-[50%] border-2 border-solid border-[#333]"
                                : "" + 'w-[100px] h-[100px] inline-block cursor-pointer m-[15px] rounded-[50%] border-2 border-solid border-[#333]'}
                            alt="animal thumbnail"
                            onClick={this.handleIndexClick}
                            data-index={index}
                        />
                    ))}
                </div>
            </div>
        );
    }
}

export default Carousel;
