import React from "react";

const Menu = () => {
  const menuItems = [
    { label: "", url: "https://gavin-bowers.arcology.builders/", image: "entertainment.png" },
    { label: "", url: "", image: "health.png" }, // ask torsten for url
    { label: "", url: "https://lanchess.arcology.builders/", image: "games.png" },
    { label: "", url: "https://macin.arcology.builders/", image: "computing.png" },
    { label: "", url: "https://macin.arcology.builders/", image: "research.png" },
    { label: "", url: "/api/allusers", image: "connected.png" },
  ];

  return (
      <div className="flex">
        {/* Side Panel */}
        <div className="w-1/4 bg-gray-200 p-4">
          {/* Add your content for the side panel here */}
          <h2 className="text-3xl font-bold mb-4">Side Panel</h2>
          {/* ... other content ... */}
        </div>

        {/* Main Menu */}
        <div className="flex-3">
          <div className="menu">
            <div>
              <h2 className="text-6xl font-bold py-8 text-center">Discover Channels</h2>
            </div>
            <div className="flex flex-wrap mb-56">
              {menuItems.map((item, index) => (
                  <div key={index} className="w-1/2 px-16 py-5 relative">
                    <button
                        className="flex-grow bg-bargray hover:bg-panelgray border-black border-2 text-white font-bold py-5 m-2 w-full"
                        style={{
                          boxShadow: "30px 35px 60px -15px rgba(0, 0, 0, 0.9)",
                          backgroundImage: `url(/channels/${item.image})`,
                          backgroundSize: "cover",
                          backgroundPosition: "center",
                          backgroundRepeat: "no-repeat",
                        }}
                        onClick={() => window.location.href = item.url}
                    >
                      {item.label}
                    </button>
                  </div>
              ))}
            </div>
          </div>
        </div>
      </div>
  );
};

export default Menu;
