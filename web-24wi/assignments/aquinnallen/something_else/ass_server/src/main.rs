use actix_web::{error,http, get, post, web, App, HttpResponse, HttpServer, Responder};
use actix_cors::Cors;
use serde::{Serialize, Deserialize};
use serde_json::json;
use actix_web::web::Json;

mod ass_reader;


#[get("/")]
async fn test() -> impl Responder {
  "Hello!!"
}

#[post("/returnass")]
async fn serve_ass(ass_req: Json<ass_reader::AssReq>) -> impl Responder {
  return HttpResponse::Ok().body(ass_reader::req_to_json(ass_req));
}

#[actix_web::main]
async fn main() ->std::io::Result<()> {
  HttpServer::new(|| {
    App::new()
      .wrap(Cors::permissive())
      .service(serve_ass)
      .service(test)
  })
  .bind(("0.0.0.0", 455))?
  .run()
  .await
}
