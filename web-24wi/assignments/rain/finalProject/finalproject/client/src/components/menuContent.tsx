import React from "react";
import { Link } from "react-router-dom";

const MenuContent: React.FC = () => {
  const menuItems = [
    { label: "Button 1", url: "/url1" },
    { label: "Button 2", url: "/url2" },
    { label: "Button 3", url: "/url3" },
    { label: "Button 4", url: "/url4" },
    { label: "Button 5", url: "https://www.external-link.com" }, // External URL
    { label: "Button 6", url: "/url6" },
  ];

  return (
    <div>
      <h2>Menu Content</h2>
      <div className="button-grid">
        {menuItems.map((item, index) => (
          <div key={index}>
            {item.url.startsWith("http") ? (
              <a href={item.url} target="_blank" rel="noopener noreferrer">
                <div className="button">{item.label}</div>
              </a>
            ) : (
              <Link to={item.url}>
                <div className="button">{item.label}</div>
              </Link>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default MenuContent;
