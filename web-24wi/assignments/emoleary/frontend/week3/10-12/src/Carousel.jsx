import { Component } from 'react';

class Carousel extends Component {
    state = {
        active: 0, 
    }

    static defaultProps = {
        images: ["http://pets-images.dev-apis.com/pets/none.jpg"]
    }

    // = () => ... : Arrow Function/Event Listener doesn't create new scope, instead its scope is Carousel
    // () { ...         : New Function : (this keyword is undefined)
    handleIndexClick = (event) => {
        this.setState({
          active: +event.target.dataset.index,
        });
      };

    render () {
        throw new Error("lol error");
        
        const { active} = this.state
        const { images } = this.props

        return (
            <div className='carousel'>
                <img src={images[active]} alt="animal hero" />
                <p>ACTIVE: {active}</p>
                <div className='carousel-smaller'>
                    {images.map((photo, index) => (
                        <img
                            onClick={this.handleIndexClick}
                            key={index}
                            src={photo}
                            className={index === active ? 'active' : ''}
                            alt="animal thumbnail"
                            data-index={index}
                        />
                    )
                )}
                </div>
            </div>
        )
    }
}

export default Carousel;
