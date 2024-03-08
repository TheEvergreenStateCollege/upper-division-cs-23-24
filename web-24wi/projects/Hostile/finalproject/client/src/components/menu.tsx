import React from "react";

const Menu = () => {
  const menuItems = [
    { label: "", url: "", image: "entertainment.png" },
    { label: "", url: "", image: "health.png" },
    { label: "", url: "", image: "games.png" },
    { label: "", url: "", image: "computing.png" },
    { label: "", url: "", image: "research.png" },
    { label: "", url: "/api/allusers", image: "connected.png" },
  ];

  return (
    <div className="menu">
      <div>
        <h2 className="text-6xl font-bold pb-10 text-center">Discover Channels</h2>
        </div>
      <div className="flex flex-wrap">
        {menuItems.map((item, index) => (
          <div key={index} className="w-1/2 p-5 relative">
            <button
              className="flex-grow text-6xl bg-bargray hover:bg-panelgray border-black border-2 text-white font-bold py-12 px-24 m-2 w-full"
              style={{
                boxShadow: "30px 35px 60px -15px rgba(0, 0, 0, 0.9)",
                backgroundImage: `url(/channels/${item.image})`,
                backgroundSize: "cover",
                backgroundPosition: "center",
              }}
              onClick={() => window.location.href = item.url}
            >
              {item.label}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Menu;
