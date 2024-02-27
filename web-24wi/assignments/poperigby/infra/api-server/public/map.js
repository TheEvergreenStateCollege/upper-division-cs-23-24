import * as d3 from "https://cdn.jsdelivr.net/npm/d3@7/+esm";

function getSVGNode(us) {
    const width = 975;
    const height = 610;

    const svg = d3.create("svg")
        .attr("viewBox", [0, 0, width, height])
        .attr("width", width)
        .attr("height", height)
        .attr("style", "max-width: 100%; height: auto;");

    const path = d3.geoPath();
    const g = svg.append("g");

    const states = g.append("g")
        .attr("fill", "#444")
        .attr("cursor", "pointer")
        .selectAll("path")
        .data(topojson.feature(us, us.objects.states).features)
        .join("path")
        .attr("d", path)
        .append("title")
        .text(d => d.properties.name);

    return svg.node();
}

const main = async () => {
    const response = await fetch("https://indira.arcology.builders/us.json");
    const us = await response.json();
    const svgNode = getSVGNode(us);
    const mapDiv = document.getElementById("map");
    mapDiv.appendChild(svgNode);
}

main();
