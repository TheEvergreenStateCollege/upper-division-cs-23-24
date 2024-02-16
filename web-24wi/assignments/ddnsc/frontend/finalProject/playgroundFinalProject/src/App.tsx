import React from "react";
import { Button } from "./components/Button";
import { ButtonProps } from "./components/Button";

function App() {
  const handleSelectItem = (item: string) => {
    console.log(item);
  };

  const buttons: ButtonProps[] = [
    {
      onClick: () => handleSelectItem("submit"),
      children: "Button 1",
    },
    {
      onClick: () => handleSelectItem("cancel"),
      children: "Button 2",
    },
    // add more buttons as needed
  ];

  return (
    <div>
      {/* render a row of buttons using the map function */}
      <div className="flex flex-row">
        {buttons.map((buttonProps, index) => (
          <div key={index} className="mx-2 mt-8 p-2 border border-gray-700">
            <Button {...buttonProps} />
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
