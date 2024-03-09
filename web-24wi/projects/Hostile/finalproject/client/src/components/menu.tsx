import React from "react";

const Menu = () => {
    const sidePanelItems = [
        { label: "", to: "", image: "sign.png" },
        { label: "", to: "", image: "post.png" },
        { label: "", to: "", image: "disc.png" },
        { label: "", to: "", image: "abyss.png" },
    ];

    const menuItems = [
        { label: "", url: "https://gavin-bowers.arcology.builders/", image: "entertainment.png" },
        { label: "", url: "", image: "health.png" }, // ask torsten for url
        { label: "", url: "https://lanchess.arcology.builders/", image: "games.png" },
        { label: "", url: "https://macin.arcology.builders/", image: "computing.png" },
        { label: "", url: "https://macin.arcology.builders/", image: "research.png" },
        { label: "", url: "/api/allusers", image: "connected.png" },
    ];

    return (
        <div className="flex" style={{ // Removed the 'px-40' padding
            backgroundImage: 'url("/public/channels/subtle-background.jpg")',
            backgroundSize: "cover",
            backgroundPosition: "center",
            backgroundRepeat: "no-repeat",
        }}>
            <div className="flex-3" style={{ width: '100%' }}> {/* Set the width to 100vw */}
                <div className="menu">
                    <div>
                        <h2 className="text-6xl font-bold py-8 px-36 text-center">Channels</h2>
                    </div>
                    <div className="flex-1 flex justify-center pb-8">
                        <img
                            src="https://www.webdesignmuseum.org/uploaded/exhibitions/web-banners-in-the-90s/at-t-the-first-banner-1994.png"
                            alt="The first web banner AT&amp;T 1994" title="The first web banner AT&amp;T 1994"
                        />
                    </div>
                    <div className="flex flex-wrap items-center">
                        {menuItems.map((item, index) => (
                            <div key={index} className="w-1/2 pr-8 pb-12 relative">
                                <a href={item.url} target="_blank" rel="noopener noreferrer">
                                    <div
                                        className="bg-bargray hover:bg-panelgray border-black border-2 text-white font-bold py-4 m-2 w-full"
                                        style={{
                                            height: "50px", // Adjust the height as needed
                                            boxShadow: "30px 35px 60px -15px rgba(0, 0, 0, 0.9)",
                                            backgroundImage: `url(/channels/${item.image})`,
                                            backgroundSize: "cover",
                                            backgroundPosition: "center",
                                            backgroundRepeat: "no-repeat",
                                        }}
                                    >
                                        {item.label}
                                    </div>
                                </a>
                            </div>

                        ))}
                        <div
                            className="bannerAd flex items-center justify-center w-full h-full pb-8"> {/* Added border style */}
                            <a>
                                <img className="border border-black"
                                    src="https://www.webdesignmuseum.org/uploaded/exhibitions/web-banners-in-the-90s/macromedia-flash-3-1998.gif"
                                    alt="Macromedia Flash 3.0 1998" title="Macromedia Flash 3.0 1998"
                                />
                            </a>
                        </div>

                    </div>
                    <div className="flex-1 flex justify-end">
                        <div className="pr-8">
                            <img
                                src="https://www.webdesignmuseum.org/uploaded/exhibitions/web-banners-in-the-90s/get-flash-player-1996.gif"
                                alt="Get Flash Player banner 1996" title="Get Flash Player 1996"/>
                        </div>
                        <div className="banner pr-8 pb-8">
                            <img
                                src="https://www.webdesignmuseum.org/uploaded/exhibitions/web-banners-in-the-90s/netscape-1995.gif"
                                alt="Netscape banner 1995"
                                title="Netscape 1995"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Menu;
