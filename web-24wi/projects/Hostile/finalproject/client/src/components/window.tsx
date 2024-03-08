import { useState } from "react";
import WindowButtons from "./WindowButtons";

const Window = ({ content }: { content?: any }) => {
  // states for background colors
  const [panl] = useState("bg-panelgray");
  const [whi] = useState("bg-white");
  const [text] = useState("text-black");
  const [changed, setChanged] = useState(false);

  // state for button size
  const [sizeb, setSizeb] = useState(60);

  // state for button margin
  const [sizer, setSizer] = useState(6);

  // state for top margin
  const [sizet, setSizet] = useState(8);

  // state for window enlarge
  const margb = `mb-${sizeb}`;
  const margr = `mr-${sizer}`;
  const margt = `mt-${sizet}`;
  const [enlarged, setEnlarged] = useState(false);

  return (
      // window component container
      <div className="flex flex-col">
        <div
            className={`flex justify-stretch flex-col ${panl} ${margb} ${margr} ${margt} border border-cus md:flex-1`}
        >
          <div className={`flex flex-row bg-blue-900 mx-1`}>
            <img
                src="/channels/eolIM.jpeg"
                className="mx-auto h-8"
                alt={"alt=\"evergreen online messenger image\""}
            />
            <p className="w-full px-1 text-lg text-white">Main Menu</p>
            <WindowButtons />
          </div>
          <div
              className={`mx-2 my-2 border-2 border-bargray md:flex-1 overflow-y-auto`}
          >
            <div
                className={`flex flex-row ${whi} ${text} `}
            >
              {content}
            </div>
          </div>
        </div>
      </div>
  );
};

export default Window;
