{
    # The packages we pull from
    inputs = {
        nixpkgs.url = "nixpkgs/nixos-24.05";
        utils.url = "github:numtide/flake-utils";
        fenix = {
            url = "github:nix-community/fenix";
            inputs.nixpkgs.follows = "nixpkgs";
        };

    };

    # Defines a function that instantiates outputs for our flake.
    outputs = {nixpkgs, utils, fenix, ...}:
        utils.lib.eachDefaultSystem ( system:
            let
                pkgs = nixpkgs.legacyPackages.${system};
                rust-toolchain = with fenix.packages.${system}; combine [
                    stable.toolchain
                    targets.wasm32-unknown-unknown.stable.rust-std
                ];
            in
                {
                    devShells.default = pkgs.mkShell {
                        packages = with pkgs; [
                            llvmPackages.bintools
                            nodejs_22
                            rust-toolchain
                            wasm-pack
                        ];
                        CARGO_TARGET_WASM32_UNKNOWN_UNKNOWN_LINKER = "lld";
                    };
                }
        );

}
