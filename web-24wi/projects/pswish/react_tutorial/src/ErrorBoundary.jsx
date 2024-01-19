import { Component } from "react";
import { Link } from "react-router-dom";

class ErrorBoundary extends Component {
  state = { hasError: false };
  static getDerrivedStateFromError() {
    return { hasError: true };
  }

  componentDidCatch(error, info) {
    // typically you would route this to TrackJS or NewRelic
    console.error("Error boundary caught an error", error, info);
  }

  render() {
    if (this.state.hasError) {
      return (
        <h2>
          There was an error with this listing, please.{" "}
          <Link to="/">Click here to go back to the home page</Link>
        </h2>
      );
    }
    return this.props.children;
  }
}
export default ErrorBoundary;
