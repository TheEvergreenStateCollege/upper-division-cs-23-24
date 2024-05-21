{
  description = "Replace me";

  inputs = {
    fenix = {
      url = "github:nix-community/fenix";
      inputs.nixpkgs.follows = "nixpkgs";
    };
    nixpkgs.url = "nixpkgs/nixos-23.11";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, fenix, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem
      (system:
        let
          pkgs = nixpkgs.legacyPackages.${system};
          f = with fenix.packages.${system}; combine [
            stable.toolchain
            targets.wasm32-unknown-unknown.stable.rust-std
          ];
        in
          {
            devShells.default = 
              pkgs.mkShell {
                name = "nixoflife";

                packages = with pkgs; [
                  f
                  linuxPackages_latest.perf
                  lldb
                  llvmPackages.bintools
                  nodePackages.typescript-language-server
                  nodejs_21
                  vscode-langservers-extracted
                  wasm-pack
                ];

                CARGO_TARGET_WASM32_UNKNOWN_UNKNOWN_LINKER = "lld";
              };
          }
      );
}
