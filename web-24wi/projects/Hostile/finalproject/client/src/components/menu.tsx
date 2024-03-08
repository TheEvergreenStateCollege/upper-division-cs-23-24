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
        <div className="flex px-40">
            <div className="flex-3">
                <div className="menu">
                    <div>
                        <h2 className="text-6xl font-bold py-8 text-center">Discover Channels</h2>
                    </div>
                    <div className="flex flex-wrap items-center pb-80">
                        {menuItems.map((item, index) => (
                            <div key={index} className="w-1/2 pr-8 py-4 relative">
                                <a href={item.url} target="_blank" rel="noopener noreferrer">
                                    <div
                                        className="flex-grow bg-bargray hover:bg-panelgray border-black border-2 text-white font-bold py-4 m-2 w-full"
                                        style={{
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
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Menu;
